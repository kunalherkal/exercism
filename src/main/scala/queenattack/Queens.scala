package queenattack

/**
  * Created by khn3193 on 4/15/16.
  */
case class Queens() {

  def boardString(w: Option[Position], b: Option[Position]): String = {
    (1 to 8).map(e => (1 to 8).map(x => "_ ").mkString.trim + "\n").mkString
  }

  def canAttack(w: Position, b: Position): Boolean = {
    false
  }
}
