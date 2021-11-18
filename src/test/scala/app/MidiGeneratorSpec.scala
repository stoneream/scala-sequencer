package app

import org.scalatest.flatspec.AnyFlatSpec

class MidiGeneratorSpec extends AnyFlatSpec {
  // todo tickの確認
  it should "generate" in {
    {
      // 1bar
      val notations = List(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      val ticks = res.map(_.getTick)
      assert(res.size == 2)
    }
    {
      // 1/2
      val notations = List(Scale.C1, Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 4)
    }
    {
      // 1/4
      val notations = List.fill[Notation](4)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 8)
    }
    {
      // 1/8
      val notations = List.fill[Notation](8)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 16)
    }
    {
      // 1/16
      val notations = List.fill[Notation](16)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 32)
    }
  }
}
