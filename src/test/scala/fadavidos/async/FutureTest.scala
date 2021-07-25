package fadavidos.async

import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FutureTest extends AnyFlatSpec {

  "Future" should "be created succesful" in {
    val greeting = Future.successful("hi")
    greeting.map( hi => assert(hi.equals("hi")))
  }

}
