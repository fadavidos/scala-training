package fadavidos.async

import org.scalatest.{Assertion, Assertions}
import org.scalatest.flatspec.AsyncFlatSpec

import scala.concurrent.Future

class FutureTest extends AsyncFlatSpec {

  behavior of "Future"

  it should "be created succesful" in {
    val expectedGreeting = "hi"
    val validation: String => Assertion = value => assert(expectedGreeting ==value)
    Future.successful("hi").map(validation)
  }

  it should "be created failed" in {
    Future.failed[String](new RuntimeException("something was wrong!!"))
      .map[Assertion] { _ => fail() }
      .recover {
        case _: RuntimeException => succeed
        case _: Exception => fail()
      }
  }

}
