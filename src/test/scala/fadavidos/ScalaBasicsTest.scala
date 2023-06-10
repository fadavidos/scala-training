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

  /*
   fold function is to aggregate values or transform the collection into a single value.
   We have three fold functions: fold, foldLeft and foldRight
   */

  "fold left" should "sum all elements of a list" in {
    val sum: (Int, Int) => Int = (num1, num2) => num1 + num2
    val numbers = List(1, 2, 3, 4)
    val result = numbers.foldLeft(0)(sum)
    assertResult(10)(result)
  }

  "fold right" should "accumulate all values in a list of Strings" in {
    val concat: (String, String) => String = (s1, s2) => s"$s2 $s1".trim
    val names = List("one", "two", "three", "four")
    val result = names.foldRight("")(concat)
    assertResult("four three two one")(result)
  }

  "fold" should "accumulate all values of a list, and return the product" in {
    val numbers = List(1, 2, 3, 4)
    val result = numbers.fold(1)(_ * _)
    assertResult(24)(result)
  }

}
