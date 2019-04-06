package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class element
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // F
  val stringToIntconverter = new Function[String, Int] {
    override def apply(value: String): Int = value.toInt
  }

  println(stringToIntconverter("3") * 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  /*
    1. a function which takes 2 strings and concatenates them
    2. go to MyList and transform the MyPredicate and MyTransformer into function types
    3. define a function which takes and int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
   */
  // 1
  val concatenator: (String, String) => String = _ + _
  println(concatenator("Hello, ", "WORLD!!!"))

  // 3
  def superAdder: Function1[Int, Function1[Int, Int]] =
    (x: Int) => (y: Int) => x + y

  println(superAdder(3)(4))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
