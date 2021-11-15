package app

import javax.sound.midi.{ MidiEvent, MidiSystem, Sequence, ShortMessage }

object Main extends App {
  val midiDevices = MidiSystem.getMidiDeviceInfo

  // デバイスを表示してみるテスト
  midiDevices.foreach { midiDeviceInfo =>
    val midiDevice = MidiSystem.getMidiDevice(midiDeviceInfo)

    val message =
      s"""instance type : ${midiDevice.getClass.getName}
         | name : ${midiDeviceInfo.getName}
         | vendor : ${midiDeviceInfo.getVendor}
         | description : ${midiDeviceInfo.getDescription}
         | version : ${midiDeviceInfo.getVersion}""".stripMargin

    println(message)
  }

  val receiver = MidiSystem.getReceiver

  val sequencer = MidiSystem.getSequencer
  sequencer.getTransmitter.setReceiver(receiver)

  // 適当なシーケンスを作る
  val sequence = new Sequence(Sequence.PPQ, 480)
  val track = sequence.createTrack()

  val noteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 93)
  val noteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 0)
  track.add(new MidiEvent(noteOn, 480))
  track.add(new MidiEvent(noteOff, 480 * 2))

  sequencer.open()
  sequencer.setSequence(sequence)
  sequencer.start()
  sequencer.setLoopCount(10)
  Thread.sleep(960 * 10)
  sequencer.close()
}
