package fadavidos

import org.scalatest.flatspec.AnyFlatSpec

class ScalaBasicsTest extends AnyFlatSpec {

  "higher-order functions" should "be assigned to a variable and executed" in {
    val multiply: (Int, Int) => Int = (x, y) => x * y
    assertResult(6)(multiply(2, 3))
  }

  it should "be passed as an argument" in {
    val formatUpperCase: String => String = (s) =>
      s"${s.toUpperCase}!"

    val formatLowerCase: String => String = (s) =>
      s"!${s.toLowerCase}"

    def applyFormat(value: String, f: String => String): String =
      f(value)

    assertResult("HELLO!")(applyFormat("hello", formatUpperCase))
    assertResult("!hello")(applyFormat("HeLLo", formatLowerCase))
  }

  it should "be returned from a method" in {

  }

}
