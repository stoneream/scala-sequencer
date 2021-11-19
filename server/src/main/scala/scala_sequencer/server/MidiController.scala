package scala_sequencer.server

import javax.sound.midi.{ MidiDevice, MidiSystem }

class MidiController {
  def getMidiDevices: List[MidiDevice.Info] = {
    MidiSystem.getMidiDeviceInfo.toList
  }
}
