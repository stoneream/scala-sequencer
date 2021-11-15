package app

import javax.sound.midi.{ MidiDevice, MidiEvent, MidiSystem, Sequence, Sequencer, ShortMessage, Synthesizer }

object Main extends App {
  val midiDevices = MidiSystem.getMidiDeviceInfo
    .map(info => (info, MidiSystem.getMidiDevice(info)))
    .filter { case (_, midiDevice) => !midiDevice.isInstanceOf[Sequencer] && !midiDevice.isInstanceOf[Synthesizer] }
  val midiOutputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxReceivers != 0 }
  val outputDeviceOpt = midiOutputDevices
    .find { case (midiDeviceInfo, _) => midiDeviceInfo.getName == "loopMIDI Port 1" }
  val midiInputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxTransmitters != 0 }

  def midiDeviceInfoStr(midiDeviceInfo: MidiDevice.Info): String = {
    s"""name : ${midiDeviceInfo.getName}
       |vendor : ${midiDeviceInfo.getVendor}
       |description : ${midiDeviceInfo.getDescription}
       |version : ${midiDeviceInfo.getVersion}""".stripMargin
  }

  outputDeviceOpt.fold {} {
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
      val noteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, 36, 127)
      val noteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, 36, 0)
      track.add(new MidiEvent(noteOn, 480))
      track.add(new MidiEvent(noteOff, 480 * 2))

      sequencer.open()
      sequencer.setSequence(sequence)
      sequencer.start()
      sequencer.setLoopCount(10)
      Thread.sleep((sequencer.getMicrosecondLength / 1000) * 10)
      sequencer.close()
  }
}
