package scala_sequencer

import Scale._

case class MidiNote(number: Int)

object MidiNote {
  def fromScale(scale: Scale): MidiNote = {
    val baseNoteNumber = scale match {
      case _: C => 0
      case _: Cs => 1
      case _: D => 2
      case _: Ds => 3
      case _: E => 4
      case _: F => 5
      case _: Fs => 6
      case _: G => 7
      case _: Gs => 8
      case _: A => 9
      case _: As => 10
      case _: B => 11
    }
    val noteNumber = baseNoteNumber + (12 * (scale.octave + 1))

    MidiNote(noteNumber)
  }
}
