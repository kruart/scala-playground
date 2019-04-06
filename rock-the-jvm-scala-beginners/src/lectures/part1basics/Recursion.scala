package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n-1}")
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }
  }
  //  println(factorial(5000)) // stackoverflow

  def anotherFactorial(n: Int): BigInt = {
    def factHelper(x: Int, acc: BigInt): BigInt =
      if (x <= 1) acc
      else factHelper(x - 1, x * acc)
    factHelper(n, 1)
  }
  println(anotherFactorial(5000))

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive
   */

  // 1
  def concatenateTailrec(aString: String, n: Int, acc: String): String = {
    if (n <= 0) acc
    else concatenateTailrec(aString, n-1, aString + acc)
  }
  println(concatenateTailrec("hello", 3, ""))

  // 2
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if(t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, isStillPrime = true)
  }
  println(isPrime(997))
  println(isPrime(998))

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
}
