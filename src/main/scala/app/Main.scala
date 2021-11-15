package app

import javax.sound.midi.MidiSystem

object Main extends App {
  // デバイスを表示してみるテスト
  MidiSystem.getMidiDeviceInfo.foreach { midiDeviceInfo =>
    val midiDevice = MidiSystem.getMidiDevice(midiDeviceInfo)

    val message =
      s"""instance type : ${midiDevice.getClass.getName}
         | name : ${midiDeviceInfo.getName}
         | vendor : ${midiDeviceInfo.getVendor}
         | description : ${midiDeviceInfo.getDescription}
         | version : ${midiDeviceInfo.getVersion}""".stripMargin

    println(message)
  }
}
