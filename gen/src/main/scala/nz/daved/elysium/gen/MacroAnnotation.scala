package nz.daved.elysium.gen

import scala.annotation.StaticAnnotation
import scala.collection.immutable._
import scala.meta.dialects.Paradise212
import nz.daved.elysium.manipulate.Implicits._

import scala.meta._

/**
  * The eventual idea here is to lift plain functions/methods into the context of a macro
  *
  * This does:
  *
  * - Check the input tree type.
  * - Check the return tree type
  * - Check all parameter types.
  * - Converts parameter literals to the literal type they are
  * - Makes parameter literals available in scope as the named parameter
  *
  * // TODO actually just call the other method, rather then manually inlining it
  * eg.
  *
  * @macroAnnotation
  * def identity(tree: meta.Tree): meta.Tree = tree
  *
  * becomes:
  *
  *
  * class identity extends StaticAnnotation {
  *   inline def apply(a: Any): Any = meta {
  *      a match {
  *        case tree: meta.Tree =>
  *         identity(t)
  *        case _ =>
  *         abort("@identity only supports meta.Tree")
  *      }
  *   }
  * }
  *
  * and
  *
  * @macroAnnotation
  * def foo[T](bar: Defn.Val)(baz: String): Defn.Val = ???
  *
  * becomes:
  *
  * class foo[T](_baz: String) extends StaticAnnotation {
  *   inline def apply(a: Any): Any = meta {
  *      val baz: String = this match {
  *        case q"new $_(Lit.String(s: String)))" =>
  *          s
  *        case _ =>
  *          abort(expected 'baz' to be a string literal)
  *      }
  *
  *      a match {
  *        case bar: Defn.Val =>
  *         foo(bar)(baz)
  *        case _ =>
  *         abort("'foo' only supports Defn.Val")
  *      }
  *   }
  * }
  *
  * which boils down to
  *
  * class MACRONAME[T](_ARGNAME: ARGTYPE) extends StaticAnnotation {
  *   inline def apply(a: Any): Any = meta {
  *      val ARGNAME: ARGTYPE = this match {
  *        case q"new $_(Lit.String(s: ARGTYPE)))" =>
  *          s
  *        case _ =>
  *          abort(expected 'ARGNAME' to be a string literal)
  *      }
  *
  *      a match {
  *        case TREENAME: TREETYPE =>
  *         MACRONAME(TREENAME)(..MACROARGS)
  *        case _ =>
  *         abort("'TREENAME' only supports TREETYPE")
  *      }
  *   }
  * }
  *
  */
class macroAnnotation extends StaticAnnotation {
  inline def apply(a: Any): Any = meta {
    val stat: Stat = macroAnnotation.asStat(a)
    val details = MacroAnnotationExtractor.unapply(stat).get
    val typeMatcher = macroAnnotation.buildTypeMatcher(details)
    val compileTimeOnly = macroAnnotation.buildCompileTimeOnly(details)
    val argMatchers: List[Stat] = macroAnnotation.buildArgsExtractor(details)
    val inMetaBlockStats: List[Stat] = argMatchers ::: typeMatcher :: Nil
    val newStat = q"inline def apply(a: Any): Any = meta { ..$inMetaBlockStats }"
    val newStats: Seq[Stat] = newStat :: Nil
    val className: Type.Name = Type.Name(details.macroName)
    q"$compileTimeOnly class $className extends scala.annotation.StaticAnnotation { ..$newStats }; $stat"
  }
}

case class MacroAnnotationDetails(
   stats: Seq[Stat],
   macroName: String,
   treeName: String,
   treeType: Pat.Type,
   args: Seq[Term.Param])

object MacroAnnotationExtractor {
  def unapply(details: Tree): Some[MacroAnnotationDetails] = {
    val applyMethod: Defn.Def = MethodExtractor.unapply(details).get
    val stats: Seq[Stat] = StatExtractor.unapply(applyMethod.body).get
    val macroName: Term.Name = applyMethod.name
    val treeType: Pat.Type = TypeSelectExtractor.unapply(applyMethod.paramss.head.head.decltpe.get).get
    val treeName: Term.Param.Name = applyMethod.paramss.head.head.name
    val args = if (applyMethod.paramss.tail.isEmpty) Nil else applyMethod.paramss.tail.head
    Some(MacroAnnotationDetails(stats, macroName.value, treeName.value, treeType, args))
  }
}

object TypeSelectExtractor {
  def unapply(arg: Type.Arg): Option[Pat.Type] =
    arg match {
      case tpe: Pat.Type =>
        Some(tpe)
      case _ =>
        abortT(arg, "Expected a Type.Select")
    }
}

object StatExtractor {
  def unapply(body: Term): Some[Seq[Stat]] = {
    body match {
      case block: Term.Block =>
        Some(block.stats)
      case other =>
        Some(other :: Nil)
    }
  }
}

object MethodExtractor {
  def unapply(a: Tree): Some[Defn.Def] = {
    a match {
      case d: Defn.Def =>
        Some(d)
      case _ =>
        abortT(a, "Currently macroAnnotation must have an apply method")
    }
  }
}

object macroAnnotation {

  def buildCompileTimeOnly(details: MacroAnnotationDetails): Mod = {
    val annotationLit = Lit.String(s"@${details.macroName} not expanded")
    mod"@scala.annotation.compileTimeOnly($annotationLit)"
  }

  /**
    * This takes the Args from def apply(i: Int, s: String)
    *
    * Into a matcher case new $_(Lit(i: Int), Lit(s: String))
    */
  def buildArgsExtractor(details: MacroAnnotationDetails): List[Defn.Val] = {
    details.args.map { a =>
      val argsType = a.decltpe.get match {
        case t @ Type.Name("Int") => t
        case t @ Type.Name("Double") => t
        case t @ Type.Name("Boolean") => t
        case t @ Type.Name("String") => t
        case t @ Type.Name("Float") => t
        case t @ Type.Name("Byte") => t
        case t @ Type.Name("Short") => t
        case t @ Type.Name("Long") => t
        case _ => abortT(a, "Unsupported type for argument in macro")
      }

      val matcherAbortLit = Lit.String(s"Args did not match the input")
      val litExtractor = p"Lit.String(i: $argsType)"
      val patternCase = p"case Term.New(Template(_, Seq(Term.Apply(_, Seq($litExtractor))), _, _)) => i"
      val defaultCase = p"case _ => abort($matcherAbortLit)"

      q"val ${Pat.Var.Term(Term.Name(a.name.value))} = this match { ..case ${patternCase :: defaultCase :: Nil} }"
    } toList
  }

  def buildTypeMatcher(details: MacroAnnotationDetails): Term.Match = {
    val pat = Pat.Var.Term(Term.Name(details.treeName))
    val matcherAbortLit = Lit.String(s"expected input tree of type ${details.treeType.syntax}")
    val argNames = details.args.map(n => Term.Name(n.name.value))
    val treeApply = Term.Apply(Term.Name(details.macroName), Seq(Term.Name(details.treeName)))
    val macroFunctionCall =  argNames match {
      case Nil => treeApply
      case _ => Term.Apply(treeApply, argNames)
    }
    val typeCase = p"case $pat: ${details.treeType} => $macroFunctionCall"
    // TODO: once in meta, change this to abort(details.treeName, $matcherAbortLit)
    val defaultCase = p"case _ => abort($matcherAbortLit)"
    q"a match { ..case ${typeCase :: defaultCase :: Nil} }"
  }

  def asStat(a: Any): Stat = {
    a match {
      case defn: Stat =>
        defn
      case _ =>
        abort("Currently macroAnnotation only supports objects")
    }
  }
}
