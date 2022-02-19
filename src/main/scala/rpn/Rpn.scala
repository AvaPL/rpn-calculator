package rpn

import rpn.Rpn.empty
import rpn.error.*
import rpn.operations.Plus

import scala.annotation.tailrec
import scala.collection.immutable.{AbstractSeq, LinearSeq}
import scala.math

sealed trait Rpn[+T]

case object Empty extends Rpn[Nothing]

case class Stack[+T](top: T, rest: Rpn[T] = Empty) extends Rpn[T]

object Stack:
  def apply[T](top: T, rest: T*): Stack[T] =
    Stack(top, Rpn(rest*))

object Rpn:
  def apply[T](elements: T*): Rpn[T] =
    elements.foldRight(empty)((top, rest) => Stack(top, rest))

  def empty[T]: Rpn[T] = Empty

  object ops:
    given [T]: Conversion[T, Rpn[T]] =
      top => Stack(top)

    given [T]: Conversion[Rpn[T], T] =
      case Stack(top, _) => top
      case Empty         => emptyStackEvaluation

    extension [T: Numeric](rpn: Rpn[T])
      def + : Rpn[T] = summon[Plus[Rpn[T]]](rpn)
