package scala_sequencer.server

import scala_sequencer.Scale.C2
import scala_sequencer.{ Bar, MidiGenerator, Scale }

import javax.sound.midi.{ MidiDevice, MidiSystem, Sequence, Sequencer, Synthesizer }

class MidiController(val midiDevice: MidiDevice) {
  // initialize
  midiDevice.open()
  private val receiver = midiDevice.getReceiver
  private val sequencer = MidiSystem.getSequencer(false)
  private val transmitter = sequencer.getTransmitter
  transmitter.setReceiver(receiver)
  sequencer.setSequence(new Sequence(Sequence.PPQ, 480))
  sequencer.open()
  sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY)
  sequencer.setTempoInBPM(120)

  def startSequencer: Unit = {
    sequencer.start()
  }

  def setTrack: Unit = {
    val sequence = new Sequence(Sequence.PPQ, 480)
    val track = sequence.createTrack()
    val kick = C2
    val pattern = kick :: kick :: kick :: kick :: Nil
    val bar = Bar(pattern)
    MidiGenerator.generate(bar).foreach(track.add)
    sequencer.setSequence(sequence)
  }

  def stopSequencer: Unit = {
    sequencer.stop()
  }
}

object MidiController {
  def init(midiDevice: MidiDevice) = {
    new MidiController(midiDevice)
  }

  def getMidiDevices: List[(MidiDevice.Info, MidiDevice)] = {
    MidiSystem.getMidiDeviceInfo.map { info =>
      val midiDevice = MidiSystem.getMidiDevice(info)
      (info, midiDevice)
    }.filter {
      case (info, midiDevice) =>
        !midiDevice.isInstanceOf[Sequencer] && !midiDevice.isInstanceOf[Synthesizer]
    }.toList
  }

  def getOutputDevices: List[(MidiDevice.Info, MidiDevice)] = getMidiDevices.filter {
    case (info, midiDevice) =>
      midiDevice.getMaxReceivers != 0
  }
}