package queenattack

/**
  * Created by khn3193 on 4/15/16.
  */
case class Queens() {

  def boardString(whiteQueen: Option[Position], blackQueen: Option[Position]): String = {
    val dummyQueen = Position(99, 99)
    val whiteQ = whiteQueen.getOrElse(dummyQueen)
    val blackQ = blackQueen.getOrElse(dummyQueen)

    val wRow = whiteQ.rowIndex
    val wColumn =  whiteQ.columnIndex
    val bRow = blackQ.rowIndex
    val bColumn =  blackQ.columnIndex

    (0 to 7).map(row => (0 to 7).map(column => (row, column) match {
      case (`wRow`, `wColumn`) => "W "
      case (`bRow`, `bColumn`) => "B "
      case _ => "_ "
    }).mkString.trim + "\n").mkString

  }

  def canAttack(whiteQueen: Position, blackQueen: Position): Boolean = {
    horizontalAttack(whiteQueen, blackQueen) || verticalAttack(whiteQueen, blackQueen) || diagonalAttack(whiteQueen, blackQueen)
  }

  def horizontalAttack(whiteQueen: Position, blackQueen: Position): Boolean = {
    whiteQueen.rowIndex == blackQueen.rowIndex
  }

  def verticalAttack(whiteQueen: Position, blackQueen: Position): Boolean = {
    whiteQueen.columnIndex == blackQueen.columnIndex
  }

  def diagonalAttack(whiteQueen: Position, blackQueen: Position): Boolean = {
    val rowDist = whiteQueen.rowIndex - blackQueen.rowIndex
    val colDist = whiteQueen.columnIndex - blackQueen.columnIndex

    Math.abs(rowDist) == Math.abs(colDist)
  }
}
