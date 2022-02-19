package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Plus[T]:
  def apply(t: T): T

private[rpn] object Plus:
  given [T](using numeric: Numeric[T]): Plus[Rpn[T]] =
    case Stack(top1, Stack(top2, rest)) =>
      Stack(numeric.plus(top1, top2), rest)
    case _ => notEnoughElements
