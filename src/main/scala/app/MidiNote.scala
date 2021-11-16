package app

case class MidiNote(number: Int)

object MidiNote {
  def fromNote(note: Note): MidiNote = {
    val baseNoteNumber = note match {
      case _: C => 0
      case _: D => 2
      case _: E => 4
      case _: F => 5
      case _: G => 7
      case _: A => 9
      case _: B => 11
    }
    val noteNumber = baseNoteNumber + (12 * (note.octave + 1))

    MidiNote(noteNumber)
  }
}