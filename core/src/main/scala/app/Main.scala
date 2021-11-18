package app

import app.Scale._
import app.Notation._
import javax.sound.midi._

object Main extends App {
  val midiDevices = MidiSystem.getMidiDeviceInfo
    .map(info => (info, MidiSystem.getMidiDevice(info)))
    .filter { case (_, midiDevice) => !midiDevice.isInstanceOf[Sequencer] && !midiDevice.isInstanceOf[Synthesizer] }
  val midiOutputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxReceivers != 0 }
  val outputDeviceOpt = midiOutputDevices
    .find { case (midiDeviceInfo, _) => midiDeviceInfo.getName == "loopMIDI Port 1" || midiDeviceInfo.getName == "bus-1" }
  val midiInputDevices = midiDevices
    .filter { case (_, midiDevice) => midiDevice.getMaxTransmitters != 0 }

  def midiDeviceInfoStr(midiDeviceInfo: MidiDevice.Info): String = {
    s"""name : ${midiDeviceInfo.getName}
       |vendor : ${midiDeviceInfo.getVendor}
       |description : ${midiDeviceInfo.getDescription}
       |version : ${midiDeviceInfo.getVersion}""".stripMargin
  }

  outputDeviceOpt.fold {
    // output device not found !!
    // do nothing
  } {
    case (midiDeviceInfo, midiDevice) =>
      println("connected")
      println(midiDeviceInfoStr(midiDeviceInfo))

      midiDevice.open()
      val receiver = midiDevice.getReceiver

      val sequencer = MidiSystem.getSequencer(false)
      val transmitter = sequencer.getTransmitter
      transmitter.setReceiver(receiver)

      val sequence = new Sequence(Sequence.PPQ, 480)
      val track = sequence.createTrack()
      sequencer.addMetaEventListener(new MetaEventListener {
        override def meta(meta: MetaMessage): Unit = {
          meta.getType match {
            case 0x2F => // トラック終端
              sequencer.close()
            case _ =>
            // do nothing
          }
        }
      })

      // BPMは120

      val kick = C2
      val hh = As2

      // 4分音符が4つ鳴る
      val pattern1 = kick :: kick :: kick :: kick :: Nil
      val pattern2 = List.fill(4)(kick) // pattern1と同義

      // 2分音符が2つなる
      val pattern3 = kick :: hh :: Nil

      // (kick hihat) x 2 = kick hihat kick hihat
      val pattern4 = List.fill(2)(kick :: hh :: Nil).flatten

      val bar = Bar(pattern4)

      val midiMessages = MidiGenerator.generate(bar)
      midiMessages.foreach(track.add)

      sequencer.open()
      sequencer.setSequence(sequence)
      sequencer.setLoopCount(0)
      sequencer.start()
  }
}
