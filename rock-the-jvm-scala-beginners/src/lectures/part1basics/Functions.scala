package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello, bot", 123))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  // WHEN YOU NEED LOOPS, USE RECURSION.
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  println(aRepeatedFunction("hello", 3))

  // returns Unit
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int):Int = a + b

    aSmallerFunction(n , n-1)
  }

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1 * 2 * 3 * .. * n
    3. A Fibonacci function
       f(1) = 1
       f(2) = 1
       f(n) = f(n-1) + f(n-2)
    4. Tests if a number is prime.
   */
  // 1
  def greetingFunc(name: String, age: Int): String =
    s"Hi, my name is $name and I am $age years old."
  println(greetingFunc("John Doe", 28))

  // 2
  def factorial(n: Int): Int = {
    @tailrec
    def _factorial(x: Int, acc: Int = 1): Int = {
      if (x <= 0) return acc
      _factorial(x - 1, acc * x)
    }
    _factorial(n)
  }
  println(factorial(5)) // 120

  // 3
  def fibonacci(n: Int): Int = {
    @tailrec
    def _fib(x: Int, prev: Int = 0, next: Int = 1): Int = x match{
      case 0 => prev
      case 1 => next
      case _ => _fib(x - 1, next, next + prev)
    }
    _fib(n)
  }
  println(fibonacci(8)) // 21
  println(fibonacci(10)) // 55

  // 4
  def isPrime(n: Int): Boolean = {
    Stream.from(2).takeWhile(p => p * p <= n).forall(n % _ != 0)
  }
  println(isPrime(997)) // true
  println(isPrime(998)) // false

  def isPrime2(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime2(997)) // true
  println(isPrime2(998)) // false
}
