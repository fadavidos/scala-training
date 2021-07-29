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

  it should "group by elements and it should count same elements, in a Map" in {
    implicit class FruitsMap[A, B](map: Map[A, List[B]]) {
      def mountValuesEqualsTo(key: A, number: Int) = map.get(key).exists(_.size == number)
    }
    val fruits = List(TANGERINE, APPLE, PEAR, PEAR, TANGERINE, TANGERINE)
    val groupedList: Map[String, List[String]] = fruits.groupBy(identity)
    assert(groupedList.mountValuesEqualsTo(TANGERINE, 3))
    assert(groupedList.mountValuesEqualsTo(APPLE, 1))
    assert(groupedList.mountValuesEqualsTo(PEAR, 2))
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

  it should "order people by age" in {
    case class Person(name: String, age: Int)
    val people: List[Person] = List(Person("Luke", 15), Person("Mark", 10), Person("Peter", 25))
    val ZAOrdered: (Person, Person) => Boolean = (a, b) => a.age < b.age
    assert(people.sortWith(ZAOrdered).startsWith(List(Person("Mark", 10))))
  }




}
