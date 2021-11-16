package app

import org.scalatest.flatspec.AnyFlatSpec

class MidiNoteSpec extends AnyFlatSpec {

  "app.MidiNoteSpec" should "fromNote" in {
    val c1 = C(1)
    val midi = MidiNote.fromNote(c1)
    assert(midi.number == 24)
  }
}
