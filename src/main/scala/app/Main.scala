package app

import app.Scale._
import app.Notation._
import javax.sound.midi._

object Main extends App {
  val midiDevices = MidiSystem.getMidiDeviceInfo
    .map(info => (info, MidiSystem.getMidiDevice(info)))
    .filter { case (_, midiDevice) => !midiDevice.isInstanceOf[Sequencer] && !midiDevice.isInstanceOf[Synthesizer] }
  val midiOutputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxReceivers != 0 }
  val outputDeviceOpt = midiOutputDevices
    .find { case (midiDeviceInfo, _) => midiDeviceInfo.getName == "loopMIDI Port 1" || midiDeviceInfo.getName == "bus-1" }
  val midiInputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxTransmitters != 0 }

  def midiDeviceInfoStr(midiDeviceInfo: MidiDevice.Info): String = {
    s"""name : ${midiDeviceInfo.getName}
       |vendor : ${midiDeviceInfo.getVendor}
       |description : ${midiDeviceInfo.getDescription}
       |version : ${midiDeviceInfo.getVersion}""".stripMargin
  }

  outputDeviceOpt.fold {
    // output device not found !!
    // do nothing
  } {
    case (midiDeviceInfo, midiDevice) =>
      println("connected")
      println(midiDeviceInfoStr(midiDeviceInfo))

      midiDevice.open()
      val receiver = midiDevice.getReceiver

      val sequencer = MidiSystem.getSequencer(false)
      val transmitter = sequencer.getTransmitter
      transmitter.setReceiver(receiver)

      val sequence = new Sequence(Sequence.PPQ, 480)
      val track = sequence.createTrack()
      sequencer.addMetaEventListener(new MetaEventListener {
        override def meta(meta: MetaMessage): Unit = {
          meta.getType match {
            case 0x2F => // トラック終端
              sequencer.close()
            case _ =>
            // do nothing
          }
        }
      })

      val bar = Bar(List.fill(8)(C2))

      val midiMessages = MidiGenerator.generate(bar)
      midiMessages.foreach(track.add)

      sequencer.open()
      sequencer.setSequence(sequence)
      sequencer.start()
      sequencer.setLoopCount(1)
  }
}
