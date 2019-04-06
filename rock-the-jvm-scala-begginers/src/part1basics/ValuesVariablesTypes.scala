package lectures.part1basics

object ValuesVariablesTypes extends App {
  // COMPILER can infer types
  val x = 42
  println(x)

  //VALS ARE IMMUTABLE
  //  x = 2 // cannot reinitialize

  val aString: String = "hello"
  val anotherString: String = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 9142874278403884183L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables ARE MUTABLE
  var aVariable: Int = 4
  aVariable = 5 // side effects
}
