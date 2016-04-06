package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
object Demo extends App {
  val factory = new RobotFactory
  val robot1 = factory.getRobot

  val robot2 = robot1.boot
  println(robot2.name)
  val robot3 = robot1.reset
  println(robot3.name)

}
