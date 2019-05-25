package homegrown.collections

trait Set extends (String => Boolean) {
  def add(input: String): Set
  def remove(input: String): Set
  def union(that: Set): Set
  def intersection(that: Set): Set
  def difference(that: Set): Set
  def isSubsetOf(that: Set): Boolean
  final def isSupersetOf(that: Set): Boolean = that.isSubsetOf(this)

  final override def equals(other: Any): Boolean = other match {
    case that: Set => this.isSubsetOf(that) && that.isSubsetOf(this)
    case _         => false
  }

  def size: Int
  final def isEmpty: Boolean = this eq Set.empty
  final def nonEmpty: Boolean = !isEmpty
  def isSingleton: Boolean
  def sample: Option[String]
}

object Set {
  private final case class NonEmpty(element: String, otherElements: Set) extends Set {
    override def apply(input: String): Boolean =
      input == element || otherElements(input)

    override def add(input: String): Set =
      if (input == element)
        this
      else
        NonEmpty(input, otherElements.add(element))

    override def remove(input: String): Set =
      if (input == element)
        otherElements
      else
        NonEmpty(element, otherElements.remove(input))

    override def union(that: Set): Set =
      otherElements.union(that.add(element))

    override def intersection(that: Set): Set = {
      val intersectionOfOthers = otherElements.intersection(that)
      if (that(element))
        intersectionOfOthers.add(element)
      else
        intersectionOfOthers
    }

    override def difference(that: Set): Set = {
      val differenceOfOthers = otherElements.difference(that)
      if (that(element))
        differenceOfOthers
      else
        differenceOfOthers.add(element)
    }

    override def isSubsetOf(that: Set): Boolean =
      that(element) && otherElements.isSubsetOf(that)

    override def hashCode(): Int =
      element.hashCode + otherElements.hashCode

    override def size: Int =
      1 + otherElements.size

    override def isSingleton: Boolean =
      otherElements.isEmpty

    override def sample: Option[String] = Some(element)
  }

  private object Empty extends Set {
    override def apply(input: String): Boolean = false
    override def add(input: String): Set = NonEmpty(input, Empty)
    override def remove(input: String): Set = this
    override def union(that: Set): Set = that
    override def intersection(that: Set): Set = this
    override def difference(that: Set): Set = this
    override def isSubsetOf(that: Set): Boolean = true
    override def size: Int = 0
    override def isSingleton: Boolean = false
    override def sample: Option[String] = None
  }

  val empty: Set = Empty
}
