package app

// 試作
abstract class Note extends Notation {
  final val PPQ: Int = 480
  val tick: Int
}

object Note {
  final object Whole extends Note {
    override val tick: Int = PPQ * 4
  }
  final object Half extends Note {
    override val tick: Int = PPQ * 2
  }
  final object Quarter extends Note {
    override val tick: Int = PPQ
  }
  final object Eighth extends Note {
    override val tick: Int = PPQ / 2
  }
  final object Sixteenth extends Note {
    override val tick: Int = PPQ / 4
  }

  final val W = Whole
  final val H = Half
  final val Q = Quarter
  final val E = Eighth
  final val S = Sixteenth
}
