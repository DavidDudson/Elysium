name := "Elysium"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
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
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-SNAPSHOT" cross CrossVersion.full),
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-RC4",
  updateOptions := updateOptions.value.withCachedResolution(true),
  coverageHighlighting := false,
  coverageEnabled := false
)

// Macro setting is any module that has macros, or manipulates meta trees
lazy val macroSettings = Seq(
  libraryDependencies += "org.scalameta" %% "scalameta" % "1.1.0-SNAPSHOT"
)

lazy val gen = (project in file("gen"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)

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

lazy val manipulateTest = (project in file("manipulate_test"))
  .settings(commonSettings: _*)
  .dependsOn(manipulate)

lazy val debug = (project in file("debug"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .settings(libraryDependencies += "com.lihaoyi" %% "pprint" % "0.4.1")
  .dependsOn(gen, core)

lazy val debugTest = (project in file("debug_test"))
  .settings(commonSettings: _*)
  .dependsOn(debug)

lazy val misc = (project in file("misc"))
  .settings(commonSettings: _*)
  .settings(macroSettings: _*)
  .dependsOn(gen, core)

lazy val miscTest = (project in file("misc_test"))
  .settings(commonSettings: _*)
  .dependsOn(misc)

lazy val all = (project in file("all"))
    .settings(Seq(aggregate in update := false))
    .aggregate(gen, core, verify, manipulate, debug, misc)

