name := "Inline Macros"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  scalacOptions += "-Xplugin-require:macroparadise",
  resolvers += Resolver.typesafeRepo("releases"),
  resolvers += Resolver.sonatypeRepo("releases"),
  resolvers += Resolver.sonatypeRepo("snapshots"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "3.0.0-M3" cross CrossVersion.full),
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-RC4",
  coverageHighlighting := false
)

lazy val root = (project in file("."))
    .settings(commonSettings:_*)
    .settings(coverageEnabled := true)
    .dependsOn(macros)

lazy val macros = (project in file("macros"))
    .settings(commonSettings:_*)
    .settings(
      libraryDependencies += "org.scalameta" %% "scalameta" % "1.0.0"
)
