package scala_sequencer

import org.scalatest.flatspec.AnyFlatSpec

class MidiGeneratorSpec extends AnyFlatSpec {
  // todo tickの確認
  it should "generate" in {
    {
      // 1bar
      val notations = List(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 3)
    }
    {
      // 1/2
      val notations = List(Scale.C1, Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 5)
    }
    {
      // 1/4
      val notations = List.fill[Notation](4)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 9)
    }
    {
      // 1/8
      val notations = List.fill[Notation](8)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 17)
    }
    {
      // 1/16
      val notations = List.fill[Notation](16)(Scale.C1)
      val bar = Bar(notations)
      val res = MidiGenerator.generate(bar)
      assert(res.size == 33)
    }
  }
}
