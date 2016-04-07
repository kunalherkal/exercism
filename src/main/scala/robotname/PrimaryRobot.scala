package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
class PrimaryRobot {

  def boot: CompleteRobot = CompleteRobot(RobotNameDirectory.getName)
}
