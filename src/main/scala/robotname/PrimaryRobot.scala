package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
class PrimaryRobot extends Robot {

  override def boot: CompleteRobot = CompleteRobot(new RobotName)

  override def reset: CompleteRobot = CompleteRobot(new RobotName)
}
