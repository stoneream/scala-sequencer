package scala_sequencer.server

import sbt.Keys.commands
import sbt._

object ServerPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override def buildSettings: Seq[Def.Setting[_]] = Def.settings()
}