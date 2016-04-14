package bankaccount

/**
  * Created by khn3193 on 4/11/16.
  */
object BankAccountDemo extends App {
  println("Hello world")

  val b1 = new BankAccount()
  val b2 = new BankAccount()
  val b3 = BankAccount()

  println("Balance: " + b1.getBalance)
  println("Balance: " + b2.getBalance)
  println("Balance: " + b3.getBalance)
}
