package scala_sequencer.server

import sbt._

object ServerPlugin extends AutoPlugin {
  override def trigger = allRequirements
  private var midiController: Option[MidiController] = None

  object autoImport {
    lazy val midiDevices = taskKey[Unit]("")
    lazy val outputDevices = taskKey[Unit]("")
    lazy val start = taskKey[Unit]("")
    lazy val stop = taskKey[Unit]("")
    lazy val setTrack = taskKey[Unit]("")
  }
  import autoImport._

  override def buildSettings: Seq[Def.Setting[_]] = Seq(
    midiDevices := midiDevicesTask.value,
    outputDevices := outputDevicesTask.value,
    start := startTask.value,
    stop := stopTask.value,
    setTrack := setTrackTask.value)

  val midiDevicesTask = Def.task {
    val (info, devices) = MidiController.getMidiDevices.unzip
    info.map(Format.midiDeviceInfo).foreach(println)
  }

  val outputDevicesTask = Def.task {
    val (info, devices) = MidiController.getOutputDevices.unzip
    info.map(Format.midiDeviceInfo).foreach(println)
  }

  val startTask = Def.task {
    val outputDeviceOpt = MidiController.getOutputDevices.find {
      case (info, _) =>
        info.getName.contains("loopMIDI Port 1")
    }

    outputDeviceOpt.fold {
      println("device not found")
    } {
      case (info, device) =>
        midiController = Some(MidiController.init(device))
        midiController.map(_.startSequencer)
    }
  }

  val setTrackTask = Def.task {
    midiController.map(_.setTrack)
  }

  val stopTask = Def.task {
    midiController.map(_.stopSequencer)
  }
}