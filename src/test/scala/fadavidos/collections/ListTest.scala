package fadavidos.collections

import org.scalatest.flatspec.AnyFlatSpec

class ListTest extends AnyFlatSpec {

  val APPLE = "APPLE"
  val PEAR = "PEAR"
  val TANGERINE = "TANGERINE"

  behavior of "List"

  it should "be created" in {
    val fruits: List[String] = List(APPLE, PEAR)
    assert(fruits.contains(APPLE))
  }

  it should "be created from a range" in {
    val fruits: List[Int] = List.range(0, 10)
    assert(fruits.size == 10)
  }

  "filter()" should "delete others elements" in {
    val fruits: List[String] = List(APPLE, PEAR, TANGERINE)
    val appleFilter: String => Boolean = (value) => value.equals(APPLE)
    assert(!fruits.filter(appleFilter).contains(PEAR))
  }

  "sorted()" should "order elements A - Z" in {
    val fruits: List[String] = List(TANGERINE, APPLE, PEAR)
    assert(fruits.sorted.startsWith(List(APPLE)))
  }

  "sortWith()" should "order elements Z - A" in {
    val fruits: List[String] = List(APPLE, TANGERINE, PEAR)
    val ZAOrdered: (String, String) => Boolean = (a, b) => a.charAt(0).toLower > b.charAt(0).toLower
    assert(fruits.sortWith(ZAOrdered).startsWith(List(TANGERINE)))
  }




}
