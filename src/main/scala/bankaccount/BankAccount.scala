package bankaccount

/**
  * Created by khn3193 on 4/11/16.
  */
case class BankAccount() {
  private var amount: Int = 0
  private var active: Boolean = true

  def getBalance: Option[Int] = active match {
    case true => Some(amount)
    case false => None
  }

  def incrementBalance(incrementBy : Int) : Option[Int] = active match {
    case true => this.synchronized {
      amount += incrementBy
      Some(amount)
    }
    case false => None
  }

  def closeAccount() = active = false
}

