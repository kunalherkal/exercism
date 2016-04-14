package bankaccount

import org.scalatest.concurrent.{Conductors, IntegrationPatience}
import org.scalatest.{FunSuite, Matchers}

class BankAccountTest extends FunSuite with Matchers with Conductors with IntegrationPatience {
  test("open account") {
    BankAccount().getBalance should be (Some(0))
  }

  test("incrementing and checking balance") {
    val acct = BankAccount()
    import acct._

    getBalance should be (Some(0))
    incrementBalance(10) should be (Some(10))
    getBalance should be (Some(10))
    incrementBalance(10) should be (Some(20))
    getBalance should be (Some(20))
  }

  test("closed account should hold no balance") {
    val acct = BankAccount()
    acct.closeAccount()
    acct.incrementBalance(10)
    acct.incrementBalance(10)
    acct.getBalance should be (None)
  }

  test("incrementing balance from multiple threads") {
    val conductor = new Conductor
    import conductor._

    val acct = BankAccount()

    thread("t1") {
      acct.incrementBalance(10)
      acct.getBalance should be (Some(10))
      beat should be (1)
      waitForBeat(2)
      acct.getBalance should be (Some(15))
    }

    thread("t2") {
      waitForBeat(1)
      acct.getBalance should be (Some(10))
      acct.incrementBalance(5)
      acct.getBalance should be (Some(15))
      beat should be (2)
    }
  }

  test("incrementing balance from multiple threads - concurrent updates") {
    val conductor = new Conductor
    import conductor._

    val acct = BankAccount()

    thread("t1") {
      for (a <- 1 to 10)
        acct.incrementBalance(10)
    }

    thread("t2") {
      for (a <- 1 to 10)
        acct.incrementBalance(5)
    }

    whenFinished {
      acct.getBalance should be (Some(150))
    }
  }
}