package scala_sequencer.server

import sbt._

object ServerPlugin extends AutoPlugin {
  override def trigger = allRequirements

  object autoImport {
    lazy val hoge = taskKey[Unit]("")
  }
  import autoImport._

  override def buildSettings: Seq[Def.Setting[_]] = Seq(hoge := hogeTask.value)

  lazy val hogeTask = Def.task {
    println("HOGE")
  }
}