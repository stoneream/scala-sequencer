package app

import org.scalatest.flatspec.AnyFlatSpec

class MidiNoteSpec extends AnyFlatSpec {

  "app.MidiNoteSpec" should "fromNote" in {
    val midi = MidiNote.fromScale(Scale.C1)
    assert(midi.number == 24)
  }
}
