import scalariform.formatter.preferences._

ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.ishikawawawa"

lazy val root = (project in file("."))
  .settings(
    name := "scala-sequencer",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.8"
    )
  )

lazy val core = project.settings(
  name := "core"
)

lazy val server = project.settings(
  name := "server"
)

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)