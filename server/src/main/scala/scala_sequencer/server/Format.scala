package scala_sequencer.server

import javax.sound.midi.MidiDevice

object Format {
  def midiDeviceInfo(m: MidiDevice.Info): String = {
    s"${m.getName} (${m.getVendor}) - ${m.getVersion}"
  }
}
