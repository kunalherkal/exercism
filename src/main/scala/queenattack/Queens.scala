package queenattack

/**
  * Created by khn3193 on 4/15/16.
  */
case class Queens() {

  def boardString(w: Option[Position], b: Option[Position]): String = (w, b) match {
    case(None, None) => (1 to 8).map(row => (1 to 8).map(column => "_ ").mkString.trim + "\n").mkString
    case _ => (0 to 7).map(row => (0 to 7).map(column =>  {
      if(row == w.get.rowIndex && column == w.get.columnIndex)
        "W "
      else if (row == b.get.rowIndex && column == b.get.columnIndex)
        "B "
      else
        "_ "
    } ).mkString.trim + "\n").mkString
  }

  def canAttack(w: Position, b: Position): Boolean = {
    false
  }
}
