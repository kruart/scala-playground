package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 18)

  // import the package
  val princess = new Princess // playground.Cinderella = fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use fully qualified name
  val date = new Date
  val sqlDate = new java.sql.Date(2018,5,4)

  // 2. use aliasing
  val sqlDateAlias = new SqlDate(2018,5,4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scalaPredef - println, ???
}
