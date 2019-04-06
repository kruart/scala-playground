package exercises

abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def filter(predicate: A => Boolean): MyList[A]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer:  A => MyList[B]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]  // concatenation

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // hofs
  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: A => B): MyList[B] =
    Cons(transformer.apply(h), t.map(transformer))

  override def flatMap[B](transformer:A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] +++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5))))
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  // hofs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+)   =
    = [].fold(6)(+)    = 6
   */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
  val listOfString: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(elem =>  Cons(elem, Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x,y) => y -x))
  println(anotherListOfIntegers.zipWith[String, String](listOfString, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val forValues = for {
    n <- listOfIntegers
    string <- listOfString
  } yield n + "-" + string
  println(forValues)
}
