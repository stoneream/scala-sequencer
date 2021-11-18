package app

case class Bar(
    notations: List[Notation],
    length: Int = 1,
    PPQ: Int = 480
) {
  // 現況はそこまで小難しいことはできないようにする
  require(List(16, 8, 4, 2, 1).contains(notations.size))
  // 1小節だけ
  require(length == 1)
}
