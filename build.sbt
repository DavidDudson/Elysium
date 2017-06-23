name := "Elysium"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.2",
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions += "-Xlint:_",
  scalacOptions += "-Ywarn-unused-import",
  scalacOptions += "-Ywarn-unused",
  scalacOptions += "-Ywarn-value-discard",
  scalacOptions += "-Ywarn-infer-any",
  scalacOptions += "-Ywarn-dead-code",
  resolvers += Resolver.typesafeRepo("releases"),
  resolvers += Resolver.sonatypeRepo("releases"),
  resolvers += Resolver.sonatypeRepo("snapshots"),
  resolvers += Resolver.bintrayRepo("scalameta", "maven"),
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M8" cross CrossVersion.full),
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0",
  updateOptions := updateOptions.value.withCachedResolution(true),
  coverageHighlighting := false,
  coverageEnabled := true
)

// Macro setting is any module that has macros, or manipulates meta trees
lazy val macroSettings = Seq(
  libraryDependencies += "org.scalameta" %% "scalameta" % "1.8.0"
)

lazy val gen = (project in file("gen"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(manipulate)

lazy val genTest = (project in file("gen_test"))
  .settings(commonSettings: _*)
  .dependsOn(gen)

lazy val core = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, manipulate)

lazy val coreTest = (project in file("core_test"))
  .settings(commonSettings: _*)
  .dependsOn(core, manipulate)

lazy val verify = (project in file("verify"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val verifyTest = (project in file("verify_test"))
  .settings(commonSettings: _*)
  .dependsOn(verify)

lazy val manipulate = (project in file("manipulate"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)

lazy val validate = (project in file("validate"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(manipulate)

lazy val debug = (project in file("debug"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val debugTest = (project in file("debug_test"))
  .settings(commonSettings: _*)
  .dependsOn(debug)

lazy val profile = (project in file("profile"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val profileTest = (project in file("profile_test"))
  .settings(commonSettings: _*)
  .dependsOn(profile)

lazy val log = (project in file("log"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val logTest = (project in file("log_test"))
  .settings(commonSettings: _*)
  .dependsOn(log)

lazy val misc = (project in file("misc"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val miscTest = (project in file("misc_test"))
  .settings(commonSettings: _*)
  .dependsOn(misc)

lazy val test = (project in file("test"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, manipulate)

lazy val testTest = (project in file("test_test"))
  .settings(commonSettings: _*)
  .dependsOn(test, manipulate)

lazy val all = (project in file("all"))
    .settings(Seq(aggregate in update := false))
    .aggregate(gen, core, verify, manipulate, validate, test, debug, misc)
