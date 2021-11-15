package app

import javax.sound.midi.{ MidiMessage, MidiSystem, ShortMessage }

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

  // 適当にMIDIメッセージを送りつける
  val receiver = MidiSystem.getReceiver
  val message = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 93)
  receiver.send(message, -1)
}
