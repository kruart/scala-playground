package lectures.part2oop

object InheritanceAndTraits extends App {
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, isCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)


  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // preventing overrides
  // 1 - use final on member (cannot be overridden)
  // 2 - use final on class itself (cannot be extended)
  // 3 - seal the class = extend classes in THIS FILE, but prevent extension in other files



}
