package fadavidos.async

import org.scalatest.Assertion
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.Random

class FutureTest extends AnyFlatSpec {

  implicit val executionContext = scala.concurrent.ExecutionContext.Implicits.global

  behavior of "Future"

  it should "be eager" in {
    var fruits = new ListBuffer[String]()
    fruits += "pear"
    def addFruit(fruit: String): Unit = {
      fruits += fruit
    }
    Future(addFruit("apple"))
    Thread.sleep(5000)
    assert(fruits.contains("apple"))
  }

  it should "memorize its value" in {
    val oneElementValidation: Int => Assertion = value => assert(value == 1)
    val randomIntFuture: Future[Int] = Future(Random.nextInt())
    val numberElements: Future[Int] = for {
      randomOne <- randomIntFuture
      randomTwo <- randomIntFuture
      randomThree <- randomIntFuture
    } yield {
      List(randomOne, randomTwo, randomThree)
        .distinct //All numbers are the same
        .length // The length of the list with different values is 1
    }
    numberElements.map(oneElementValidation)
  }

  "succesful()" should "be created" in {
    val expectedGreeting = "hi"
    val validation: String => Assertion = value => assert(expectedGreeting ==value)
    Future.successful("hi").map(validation)
  }

  "failed()" should "be created" in {
    Future.failed[String](new RuntimeException("something was wrong!!"))
      .map[Assertion] { _ => fail() }
      .recover {
        case _: RuntimeException => succeed
        case _: Exception => fail()
      }
  }

}
