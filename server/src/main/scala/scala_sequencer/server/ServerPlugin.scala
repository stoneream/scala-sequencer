package scala_sequencer.server

import sbt._

object ServerPlugin extends AutoPlugin {
  override def trigger = allRequirements

  object autoImport {
    lazy val hoge = taskKey[Unit]("")
    lazy val midiDevices = taskKey[Unit]("")
  }
  import autoImport._

  override def buildSettings: Seq[Def.Setting[_]] = Seq(
    hoge := hogeTask.value,
    midiDevices := midiDevicesTask.value)

  val midiController = new MidiController

  lazy val hogeTask = Def.task {
    println("HOGE")
  }

  val midiDevicesTask = Def.task {
    val devices = midiController.getMidiDevices
    devices.map(Format.midiDeviceInfo).foreach(println)
  }
}