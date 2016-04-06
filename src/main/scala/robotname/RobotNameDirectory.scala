package robotname

/**
  * Created by khn3193 on 4/6/16.
  */
class RobotNameDirectory {
  var names : List[RobotName] = List.empty

  def getName : RobotName = {
    val name = new RobotName

    if(!names.contains(name))
      names = name :: names
    else
      getName

    name
  }
}
