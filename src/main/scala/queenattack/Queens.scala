package queenattack

/**
  * Created by khn3193 on 4/15/16.
  */
case class Queens() {

  def boardString(whiteQueen: Option[Position], blackQueen: Option[Position]): String = (whiteQueen, blackQueen) match {

    case (Some(Position(_, _)), Some(Position(_, _))) => {
      val wRow = whiteQueen.get.rowIndex
      val wColumn = whiteQueen.get.columnIndex
      val bRow = blackQueen.get.rowIndex
      val bColumn = blackQueen.get.columnIndex

      (0 to 7).map(row => (0 to 7).map(column => (row, column) match {
        case (`wRow`, `wColumn`) => "W "
        case (`bRow`, `bColumn`) => "B "
        case _ => "_ "
      }).mkString.trim + "\n").mkString
    }
    case _ => (0 to 7).map(row => (0 to 7).map(column => "_ ").mkString.trim + "\n").mkString
  }

  def canAttack(w: Position, b: Position): Boolean = {
    false
  }
}
