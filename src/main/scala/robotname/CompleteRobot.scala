package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
case class CompleteRobot(val name: RobotName) extends Robot {

  override def boot: CompleteRobot = throw new UnsupportedOperationException

  override def reset: CompleteRobot = CompleteRobot(new RobotName)

}
