package app

trait Notation {
  val PPQ = 480
}

object Notation {
  case class Rest() extends Notation {}

  final val R = Rest()
}