package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
class RobotName {
  val value = formName

  private def getRandomStringPart : String = {
    val random = new scala.util.Random

    def randomString(alphabet: String)(n: Int): String =
      Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString

    def randomAlphanumericString(n: Int) =
      randomString("abcdefghijklmnopqrstuvwxyz".toUpperCase)(n)

    randomAlphanumericString(2)
  }

  private def getRandomNumericalPart : Int = {
    val rnd = new scala.util.Random
    val range = 100 to 200
    range(rnd.nextInt(range length))
  }

  def formName : String = {
    getRandomStringPart ++ getRandomNumericalPart.toString
  }

  override def toString = s"RobotName(value=$value)"
}
