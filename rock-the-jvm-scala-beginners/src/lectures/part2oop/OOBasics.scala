package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  person.greet("Daniel")
  person.greet()

  // writer and novel
  val writer = new Writer("Taras", "Shevchenko", 1814)
  val novel = new Novel("Kobzar", 1840, writer)
  println(novel.authorAge)
  println(novel.isWrittenBy(writer))
  println(novel.copy(1841).authorAge) // +1

  //counter
  val counter = new Counter(0)
  println(counter.count) // 0
  println(counter.increment.count) // 1
  println(counter.decrement.count) // -1
  println(counter.increment(5).count) // 5
  println(counter.decrement(5).count) // -5

}

class Person(name: String, val age: Int = 0) {
  val x = 2
  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  //overloading
  def greet(): Unit = println(s"Hi, i am ${this.name}")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy(new year of release) = new instance of Novel
 */
class Writer(val firstName: String, val surname: String, val year: Int) {
  def fullname: String = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.year
  def isWrittenBy(author: Writer): Boolean = author.fullname == this.author.fullname
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement ==> new Counter
    - overload inc/dec to receive an amount
 */
class Counter(val count: Int) {
  def increment= new Counter(count + 1)
  def decrement= new Counter(count - 1)

  def increment(amount: Int): Counter = new Counter(count + amount)
  def decrement(amount: Int): Counter = new Counter(count - amount)
}

