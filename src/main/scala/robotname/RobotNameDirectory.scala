package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
object RobotNameDirectory {
  var names : List[RobotName] = List.empty

  def getName : RobotName = {
    val name = RobotName(RobotNameDirectory.formName)

    if(!names.contains(name)) {
      names = name :: names
      name
    }
    else
      getName
  }

  private def getRandomStringPart : String = {
    val random = new scala.util.Random

    def randomString(alphabet: String)(n: Int): String =
      Stream.continually(random.nextInt(alphabet.length)).map(alphabet).take(n).mkString

    def randomAlphanumericString(n: Int) =
      randomString("abcdefghijklmnopqrstuvwxyz".toUpperCase)(n)

    randomAlphanumericString(2)
  }

  private def getRandomNumericalPart : Int = {
    val rnd = new scala.util.Random
    val range = 100 to 200
    range(rnd.nextInt(range.length))
  }

  def formName : String = {
    getRandomStringPart ++ getRandomNumericalPart.toString
  }
}
