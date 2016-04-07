package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
class RobotFactory {

  def getRobot : PrimaryRobot = {
    new PrimaryRobot
  }
}
