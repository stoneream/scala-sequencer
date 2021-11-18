package app

trait Scale extends Notation {
  require(octave >= 0 && octave <= 9)
  val octave: Int
}

object Scale {

  // todo 半音上げ・下げについては全音の拡張として実装するか検討中...
  case class C(octave: Int) extends Scale {}

  case class Cs(octave: Int) extends Scale {}

  case class D(octave: Int) extends Scale {}

  case class Ds(octave: Int) extends Scale {}

  case class E(octave: Int) extends Scale {}

  case class F(octave: Int) extends Scale {}

  case class Fs(octave: Int) extends Scale {}

  case class G(octave: Int) extends Scale {}

  case class Gs(octave: Int) extends Scale {}

  case class A(octave: Int) extends Scale {}

  case class As(octave: Int) extends Scale {}

  case class B(octave: Int) extends Scale {}

  final val C0 = C(0)
  final val Cs0 = Cs(0)
  final val D0 = D(0)
  final val Ds0 = Ds(0)
  final val E0 = E(0)
  final val F0 = F(0)
  final val Fs0 = Fs(0)
  final val G0 = G(0)
  final val Gs0 = Gs(0)
  final val A0 = A(0)
  final val As0 = As(0)
  final val B0 = B(0)
  final val C1 = C(1)
  final val Cs1 = Cs(1)
  final val D1 = D(1)
  final val Ds1 = Ds(1)
  final val E1 = E(1)
  final val F1 = F(1)
  final val Fs1 = Fs(1)
  final val G1 = G(1)
  final val Gs1 = Gs(1)
  final val A1 = A(1)
  final val As1 = As(1)
  final val B1 = B(1)
  final val C2 = C(2)
  final val Cs2 = Cs(2)
  final val D2 = D(2)
  final val Ds2 = Ds(2)
  final val E2 = E(2)
  final val F2 = F(2)
  final val Fs2 = Fs(2)
  final val G2 = G(2)
  final val Gs2 = Gs(2)
  final val A2 = A(2)
  final val As2 = As(2)
  final val B2 = B(2)
  final val C3 = C(3)
  final val Cs3 = Cs(3)
  final val D3 = D(3)
  final val Ds3 = Ds(3)
  final val E3 = E(3)
  final val F3 = F(3)
  final val Fs3 = Fs(3)
  final val G3 = G(3)
  final val Gs3 = Gs(3)
  final val A3 = A(3)
  final val As3 = As(3)
  final val B3 = B(3)
  final val C4 = C(4)
  final val Cs4 = Cs(4)
  final val D4 = D(4)
  final val Ds4 = Ds(4)
  final val E4 = E(4)
  final val F4 = F(4)
  final val Fs4 = Fs(4)
  final val G4 = G(4)
  final val Gs4 = Gs(4)
  final val A4 = A(4)
  final val As4 = As(4)
  final val B4 = B(4)
  final val C5 = C(5)
  final val Cs5 = Cs(5)
  final val D5 = D(5)
  final val Ds5 = Ds(5)
  final val E5 = E(5)
  final val F5 = F(5)
  final val Fs5 = Fs(5)
  final val G5 = G(5)
  final val Gs5 = Gs(5)
  final val A5 = A(5)
  final val As5 = As(5)
  final val B5 = B(5)
  final val C6 = C(6)
  final val Cs6 = Cs(6)
  final val D6 = D(6)
  final val Ds6 = Ds(6)
  final val E6 = E(6)
  final val F6 = F(6)
  final val Fs6 = Fs(6)
  final val G6 = G(6)
  final val Gs6 = Gs(6)
  final val A6 = A(6)
  final val As6 = As(6)
  final val B6 = B(6)
  final val C7 = C(7)
  final val Cs7 = Cs(7)
  final val D7 = D(7)
  final val Ds7 = Ds(7)
  final val E7 = E(7)
  final val F7 = F(7)
  final val Fs7 = Fs(7)
  final val G7 = G(7)
  final val Gs7 = Gs(7)
  final val A7 = A(7)
  final val As7 = As(7)
  final val B7 = B(7)
  final val C8 = C(8)
  final val Cs8 = Cs(8)
  final val D8 = D(8)
  final val Ds8 = Ds(8)
  final val E8 = E(8)
  final val F8 = F(8)
  final val Fs8 = Fs(8)
  final val G8 = G(8)
  final val Gs8 = Gs(8)
  final val A8 = A(8)
  final val As8 = As(8)
  final val B8 = B(8)
  final val C9 = C(9)
  final val Cs9 = Cs(9)
  final val D9 = D(9)
  final val Ds9 = Ds(9)
  final val E9 = E(9)
  final val F9 = F(9)
  final val Fs9 = Fs(9)
  final val G9 = G(9)
  final val Gs9 = Gs(9)
  final val A9 = A(9)
  final val As9 = As(9)
  final val B9 = B(9)

  /*
  def generator: String = {
    (0 to 9).map { octave =>
      List("C", "Cs", "D", "Ds", "E", "F", "Fs", "G", "Gs", "A", "As", "B").map { scale =>
        val memberName = s"$scale$octave"
        val initClass = s"$scale($octave)"
        s"final val $memberName = $initClass"
      }.mkString("\n")
    }.mkString("\n")
  }
   */
}
