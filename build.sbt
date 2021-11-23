import scalariform.formatter.preferences._

ThisBuild / scalaVersion     := "2.12.15"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.ishikawawawa"

lazy val root = (project in file("."))
  .settings(
    name := "scala-sequencer"
  ).disablePlugins(ServerPlugin)

lazy val core = (project in file("core"))
  .settings(
    name := "scala-sequencer-core",
    libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.8"
    )
  ).disablePlugins(ServerPlugin)

lazy val server = (project in file("server"))
  .enablePlugins(SbtPlugin)
  .disablePlugins(ServerPlugin)
  .aggregate(core)
  .dependsOn(core)
  .settings(
    name := "scala-sequencer-server"
  )

lazy val example = (project in file("example"))
  .settings(
    name := "scala-sequencer-example"
  )

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)