package app

trait Note {
  require(octave >= 1)
  val octave: Int
}

case class C(octave: Int) extends Note
case class D(octave: Int) extends Note
case class E(octave: Int) extends Note
case class F(octave: Int) extends Note
case class G(octave: Int) extends Note
case class A(octave: Int) extends Note
case class B(octave: Int) extends Note
