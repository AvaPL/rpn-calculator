sealed trait Rpn[+T: Numeric]

case class EmptyStack[T: Numeric]() extends Rpn[T]

case class Stack[T: Numeric](top: T, rest: Rpn[T]) extends Rpn[T]

object Stack {
  def apply[T: Numeric](top: T): Stack[T] = Stack(top, EmptyStack())
}

object Rpn:
  def empty[T: Numeric]: EmptyStack[T] = EmptyStack()

  object ops:
    given [T: Numeric]: Conversion[T, Rpn[T]] with
      override def apply(top: T): Rpn[T] = Stack(top)

    given [T: Numeric]: Conversion[Rpn[T], T] with
      override def apply(rpn: Rpn[T]): T =
        rpn match {
          case Stack(top, _) => top
          case EmptyStack() =>
            throw new NoSuchElementException("Empty stack in Rpn")
        }
