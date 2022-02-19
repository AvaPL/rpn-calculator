package rpn

import scala.collection.immutable.{AbstractSeq, LinearSeq}
import scala.math

sealed trait Rpn[+T: Numeric]:
  def + : Rpn[T]

case class EmptyStack[+T: Numeric]() extends Rpn[T]:
  override def + : Rpn[T] = notEnoughElements

case class Stack[+T](top: T, rest: Rpn[T])(using numeric: Numeric[T])
    extends Rpn[T]:
  import numeric.*

  override def + : Rpn[T] =
    rest match {
      case Stack(top, rest) => Stack(this.top + top, rest)
      case EmptyStack()     => notEnoughElements
    }

object Stack {
  def apply[T: Numeric](top: T): Stack[T] = Stack(top, EmptyStack())
}

object Rpn:
  def apply[T: Numeric](elements: T*): Rpn[T] =
    elements.foldRight(empty)((top, rest) => Stack(top, rest))

  def empty[T: Numeric]: Rpn[T] = EmptyStack()

  object ops:
    given [T: Numeric]: Conversion[T, Rpn[T]] with
      override def apply(top: T): Rpn[T] = Stack(top)

    given [T: Numeric]: Conversion[Rpn[T], T] with
      override def apply(rpn: Rpn[T]): T =
        rpn match {
          case Stack(top, _) => top
          case EmptyStack()  => emptyStackEvaluation
        }
