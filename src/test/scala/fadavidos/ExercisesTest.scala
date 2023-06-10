package fadavidos

import org.scalatest.flatspec.AnyFlatSpec

class ExercisesTest extends AnyFlatSpec {

  "exercise" should "Calculate the sum of squares of all even numbers from a given list" in {
    val numbers = List(2, 4, 5, 6, 7, 10, 36)
    def sumOfSquaresOfEvens(numbers: List[Int]): Int =
      numbers.filter(_ % 2 == 0).map( number => number * number).sum

    assertResult(1452)(sumOfSquaresOfEvens(numbers))
  }

  it should "Implement a function to find the largest prime factor of a given number" in {
    def largestPrimeFactor(number: Long): Long = {
      def factors(num: Long, primeNumber: Int): List[Long] = {
        if(num == 1)
          return Nil

        if(num % primeNumber == 0)
          primeNumber :: factors(num / primeNumber, primeNumber)
        else
         factors(num, primeNumber + 1)
      }

      val factorNumbers: List[Long] = factors(number, 2)
      println(factorNumbers)
      factorNumbers.max
    }

    assertResult(2)(largestPrimeFactor(8))
    assertResult(5)(largestPrimeFactor(10))
    assertResult(5)(largestPrimeFactor(15))
    assertResult(3803)(largestPrimeFactor(1234567890L))
  }

  it should "Given a list of words, find the three longest words that start with a vowel and end with a consonant. " +
    "Return the result in descending order of word length" in {

    val vowels = List("a","e","i","o","u")

    def findLongestWords(list: List[String]): List[String] = {
      val startWithVowelEndWithConsonant: String => Boolean = word => {
        val headOp = word.toList.headOption
        val lastOp = word.toList.lastOption
        (headOp, lastOp) match {
          case (Some(head), Some(last)) =>
            vowels.contains(head.toString.toLowerCase) && !vowels.contains(last.toString.toLowerCase()) && last.isLetter
          case _ => false
        }
      }
      list.filter(startWithVowelEndWithConsonant)
        .sortBy(_.length)(Ordering[Int].reverse)
        .take(3)
    }

    val words = List("apple", "banana", "cat", "elephant", "orange", "zebra", "iguana", "experiment")

    assertResult(List("experiment", "elephant"))(findLongestWords(words))
  }


}
