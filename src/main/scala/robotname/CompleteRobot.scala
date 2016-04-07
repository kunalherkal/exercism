package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
case class CompleteRobot(name: RobotName) {

  def reset: CompleteRobot = CompleteRobot(RobotNameDirectory.getName)
}
