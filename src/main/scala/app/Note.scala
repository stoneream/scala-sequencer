package app

sealed trait Note {
  require(octave >= 0 && octave <= 9)
  val octave: Int
}

object Note {

  case class C(octave: Int = 1) extends Note

  case class D(octave: Int = 1) extends Note

  case class E(octave: Int = 1) extends Note

  case class F(octave: Int = 1) extends Note

  case class G(octave: Int = 1) extends Note

  case class A(octave: Int = 1) extends Note

  case class B(octave: Int = 1) extends Note

}
