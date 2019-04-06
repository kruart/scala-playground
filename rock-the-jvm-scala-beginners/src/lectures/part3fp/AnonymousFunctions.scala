package lectures.part3fp

object AnonymousFunctions extends App {
  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething = () => 3

  println(justDoSomething)
  println(justDoSomething())

  // curly braces with lambdas
  val stringToInt = {(str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent ot (a,b) => a + b

  /*
    1. MyList: replace all FunctionX calls with lmblas
    2. rewrite the "special" adder as an anonymous function
   */
  def superAdder = (x: Int) => (y: Int) => x + y
  println(superAdder(3)(4))
}
