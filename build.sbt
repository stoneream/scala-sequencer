import scalariform.formatter.preferences._

ThisBuild / scalaVersion     := "2.12.15"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.ishikawawawa"

lazy val root = (project in file("."))
  .settings(
    name := "scala-sequencer"
  )

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.8"
    )
  )

lazy val server = (project in file("server"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "scala-sequencer-server",
  )

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)