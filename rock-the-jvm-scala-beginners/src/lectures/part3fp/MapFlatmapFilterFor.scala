package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3)
  val chars = List('a', 'b', 'c', 'd')

  // "iterating"
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations1 = for (
    num <- numbers if num % 2 == 0;
    ch <- chars
  ) yield ch+""+num
  println(forCombinations1)

  for (
    n <- numbers
  ) println(n)

  // syntax overload
  list.map { x => x * 2 }

  /*
    1. MyList supports for comprehensions?
       map(f: A => B) => MyLIst[B]
       filter(p: A => Boolean) => MyList[A]
       flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
   */
}
