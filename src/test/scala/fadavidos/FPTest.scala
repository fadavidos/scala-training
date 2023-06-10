package fadavidos

import org.scalatest.flatspec.AnyFlatSpec

class FPTest extends AnyFlatSpec {

  "type class" should "allow us to implement different behavior when we have different data types" in {

    trait JsonSerializable[A] {
      def toJson(value: A): String
    }

    object JsonSerializableInstances {
      implicit val intJsonSerializable: JsonSerializable[Int] = (value: Int) => value.toString

      implicit val stringJsonSerializable: JsonSerializable[String] = new JsonSerializable[String] {
        override def toJson(value: String): String = s"""$value"""
      }

      implicit val doubleJsonSerializable: JsonSerializable[Double] = new JsonSerializable[Double] {
        override def toJson(value: Double): String = value.toString
      }
    }

    object JsonSyntax{
      implicit class JsonSerializableOps[A](value: A)(implicit ev: JsonSerializable[A]){
        def convert: String = ev.toJson(value)
      }
    }

    import JsonSerializableInstances._
    import JsonSyntax._

    val jsonInt: String = 42.convert
    val jsonDouble: String = 3D.convert
    val jsonString: String = "hello".convert

    assertResult("42")(jsonInt)
    assertResult("3.0")(jsonDouble)
    assertResult("hello")(jsonString)
  }

  "monoid" should "combine elements using fold" in {

    // My own type class
    trait MyMonoid[A]{
      def combine(a1: A, a2: A): A
      def empty:A
    }

    object StringMonoid extends MyMonoid[String] {
      override def combine(a1: String, a2: String): String = s"$a1 $a2".trim

      override def empty: String = ""
    }

    val list = List("one", "two", "three", "four")
    val result = list.fold(StringMonoid.empty)(StringMonoid.combine)

    assertResult("one two three four")(result)

  }

}
