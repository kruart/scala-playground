package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  person.greet("Daniel")
  person.greet()
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
