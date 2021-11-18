package app

import javax.sound.midi.{ MidiEvent, ShortMessage }

object MidiGenerator {
  val PPQ = 480

  def generate(bar: Bar): List[MidiEvent] = {
    val notations = bar.notations
    val barTick = PPQ * 4 * bar.length // 480 * 4 * 1 = 1920
    val notePerTick = barTick / bar.notations.size // 1920 / (1|2|4|8|16)
    val stop = new ShortMessage(ShortMessage.STOP)
    val stopEvent = new MidiEvent(stop, barTick)

    notations.zipWithIndex.foldRight(stopEvent :: Nil: List[MidiEvent]) {
      case ((notation, index), b) =>
        notation match {
          case scale: Scale =>
            val midiNote = MidiNote.fromScale(scale)
            // 一旦固定
            val velocity = 127
            val noteOn = new ShortMessage(ShortMessage.NOTE_ON, midiNote.number, velocity)
            val noteOff = new ShortMessage(ShortMessage.NOTE_OFF, midiNote.number, 0)

            new MidiEvent(noteOff, notePerTick * (index + 1)) :: new MidiEvent(noteOn, notePerTick * index) :: b
          case _: Notation.Rest => b
        }
    }.reverse
  }

}
