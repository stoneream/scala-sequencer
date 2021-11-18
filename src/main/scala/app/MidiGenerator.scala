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

    val midiEvents = notations.zipWithIndex.map {
      case (notation, index) =>
        notation match {
          case scale: Scale =>
            val midiNote = MidiNote.fromScale(scale)
            // 一旦固定
            val velocity = 127
            val noteOn = new ShortMessage(ShortMessage.NOTE_ON, midiNote.number, velocity)
            val noteOff = new ShortMessage(ShortMessage.NOTE_OFF, midiNote.number, 0)

            val noteOnEvent = new MidiEvent(noteOn, notePerTick * index)
            // 同じノートを続けて叩くときにちょっと動きが変になるので1tickだけ引く
            val noteOffEvent = new MidiEvent(noteOff, notePerTick * (index + 1) - 1)
            noteOnEvent :: noteOffEvent :: Nil
          case _: Notation.Rest => Nil
        }
    }.flatten

    (stopEvent :: Nil) ::: midiEvents
  }

}
