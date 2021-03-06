# Roadmap

## MetaApi (Via implicits, where applicable)

Some/All of this may end up in the scala.meta library semantic api

- [x] appendStat - Appends a statement to the stats
- [x] prependStat - prepends a statement to the stats
- [x] replaceStats - replace current stats with the supplied
- [x] deleteStats - replace current stats with the supplied
- [x] simpleName - name of the Term (no package info)
- [ ] fullName - name of the Term inlcuding package info
- [ ] addField
- [ ] addMethod
- [ ] parameters
- [ ] implicitParameters

## Annotation Macros

### Decorating
- [ ] @Decorate
- [ ] @DecorateGetters
- [ ] @DecorateSetters
- [ ] @DecorateFields
- [ ] @DecorateMethods

### Debugging
- [ ] @Spy - Keeps track of updates to an object
- [ ] @SpyPrint - Prints updates to a class/object
- [ ] @Mock - Returns a constant rather then running the function for debugging purposes
- [ ] @MockIf - Same as mock except takes a boolean supplier
- [ ] @Hide - Unit methods only, if the flag is true, method will be replace with noop.
- [ ] @Time - Prints the execution time of a method
- [ ] @PrintOnExecute - Prints the method name and parameters on execution
- [ ] @Profile - Accumulate statistics about methods that can then be accessed and printed
- [ ] @Monitor - Like @Profile, except no accumulation, so that graphs can be made from the data etc.
- [ ] @Debug - Only execute the method during debug sessions
- [ ] @WhatAmI - Prints what type of Defn the annotee is
- [ ] @Production - Only execute the method in production
- [ ] @Benchmark - Generate a benchmark method that will run this method 1 million times

### Case class
- [ ] @Case - Equivalent to "case class"
- [ ] @Equals - Generate equals and hashcode
- [ ] @HashCode - Generate hashcode
- [ ] @ToString - Generate toString
- [ ] @PrettyPrint - Generate a toString with PPrint support

### Meta Programming
- [ ] @Macro - Inserts the inline and meta block for you
- [ ] @AnnotationMacro - Converts a def X into an annotation macro of @X

## Identities
- [x] @Identity - No-op
- [ ] @Copy - Typeclass of Macros that literally just duplicate the contents
- [x] @CopyDef
- [ ] @CopyVal
- [ ] @CopyVar
- [ ] @CopyTrait
- [ ] @CopyClass
- [ ] @CopyObject
- [ ] @CopyType

## Printers
- [x] @PrintStructure - show structure of anything annotatable
- [x] @PrintSyntax - show syntax of anything annotatable
- [ ] @PrintMods
- [ ] @PrintFields
- [ ] @PrintParams
- [ ] @PrintStatements
- [ ] @PrintSuperClass
- [ ] @PrintTraits
- [ ] @PrintMethods
- [ ] @PrintFunctions

### Optimisation
- [ ] @Specialize - Identical to scala @Specialize

### Java Compat
- [ ] @Of - Create an X.of method for ever X.apply method (Guava syntax)
- [ ] @BeanProperty - Identical to scala @BeanProperty
- [ ] @BooleanBeanProperty - Identical to scala @BooleanBeanProperty
- [ ] @GenJava - generate an identical method that returns a java collection rather then scala

### SanityChecks - All done via assertions
- [ ] @NonNull - add assertions for non-null results
- [ ] @NonNullParams - add assertions for non-null parameters
- [ ] @Nat - add assertions for natural results
- [ ] @NatParams - add assertions for natural parameters
- [ ] @NonEmpty - add assertions for non-empty traversable
- [ ] @Singleton - Verify that it is a one element list
- [ ] @Empty - Verify the object is empty
- [ ] @Immutable - Verify only val's, no vars
- [ ] @ImmutableCollections - Only ImmutableCollections
- [ ] @MutableCollections - Marker to tell @ImmutableCollection to ignore this method
- [ ] @LazyCollections - Only Views are allowed
- [ ] @Lazy - All fields are lazy
- [ ] @Struct - Verify no behavior only getters and setters
- [ ] @NoFields - Verify no fields only behavioral implications
- [ ] @NoNullItems - Verify a traversable has no nulls in it
- [ ] @Pure - Verify no side effects (Is this even possible without the scala effect api)

### Threading
- [ ] @NotThreadSafe - Verify the object is only accessed via one thread it's entire lifetime
- [ ] @ThreadSafe - Marker to tell @NotThreadSafe to ignore this method
- [ ] @NotConcurrentAccess - Verify the object is only accessed via one thread at a time (Unlike @Synchronize this would actually throw on concurrent access, rather then waiting)
- [ ] @ConcurrentAccess - Marker to tell @ConcurrentAccess to ignore this method
- [ ] @Thread - Verify thread is correct
- [ ] @NotThread - Verify thread is not X thread
- [ ] @Synchronize - Synchronize all access to a class/trait/object
- [ ] @NoSynchronize - Marker to tell @Synchronize to ignore this method
- [ ] @ReadWriteLocked - Create a readwrite lock and ensure that
- [ ] @Read - Marker - unnecessary as all methods will take the read lock by default
- [ ] @Write - Take the writelock while inside this method

### Testing
- [ ] @TestOnly - Remove the method/class in production?
- [ ] @VisibleForTesting - Semi-Marker - Verify that the modifier is not currently private (thus unneccesary)

### Structural Design Patterns - Unsure if these will be marker or behavioral or exist at all
- [ ] @Decorator
- [ ] @Delegate
- [ ] @Adapter
- [ ] @Facade
- [ ] @Flyweight
- [ ] @Composite
- [ ] @Bridge
- [ ] @Proxy

### Creational Design Patterns - Unsure if these will be marker or behavioral or exist at all
- [ ] @Factory
- [ ] @Builder
- [ ] @Prototype
- [ ] @Singleton

### Behavioral Design Patterns - Unsure if these will be marker or behavioral or exist at all
- [ ] @ChainOfReponsibility
- [ ] @Command
- [ ] @Interpreter
- [ ] @Iterator
- [ ] @Mediator
- [ ] @Momento
- [ ] @Observer
- [ ] @State
- [ ] @Strategy
- [ ] @TemplateMethod
- [ ] @Visitor

### Constraints
- [ ] Commutative
- [ ] Associative
- [ ] Distributive
- [ ] Covariant
- [ ] Invariant
- [ ] Bivariant
- [ ] Contravariant

### FP Design patterns - Unsure if these will be marker or behavioral
- [ ] @Typeclass
- [ ] @Monad
- [ ] @Monoid

### Potential Library Dependants
- [ ] Scalaz:@Show - Generate a Show typeclass for this object
- [ ] Scalaz:@Eq - Generate a Eq typeclass for this object
- [ ] Many:@Option - Generate toXOption for Scala/ScalaZ/Shapeless etc.
- [ ] Many:@Logger - Generate a logger for the class

### Redundant - For completion/testing purposes - DO NOT USE IN PRODUCTION
- [ ] @ToVal - Convert var to val
- [ ] @ToVar - Convert val to var
- [ ] @Lazy - Make lazy
- [ ] @Privatise - convert any methods/fields etc prefixed with _ to private (from python)
- [ ] @Private - make private
- [ ] @PackagePrivate - make package private
- [ ] @Final - make final
- [ ] @Public - Convert things to public ones
- [ ] @Unseal - Convert sealed things to unsealed
- [ ] @NoSynchronize
- [ ] @Rename - Change the name of a class/trait/field/method etc.
- [ ] @Delete - Return an empty tree


### Other
- [ ] @Enum - A scala enum type, the way it should have been
- [ ] @Refine(a, b) - generates "def b(params) = super.a(params)"
- [x] @Operator(symbol) - generate a method with the symbolic operator
- [x] @Alias(name) - generate a second method/class identical but with a different name

## Def Macros
- TBD

## Collections

- [ ] Option - Unboxed option type (no runtime overhead)
- [ ] Try - Unboxed Try type
