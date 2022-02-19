package rpn.operations

import rpn.{Rpn, Stack, Empty}
import rpn.error._

private[rpn] trait Sub[T]:
  def apply(t: T): T

object Sub:
  given rpnInstance[T](using numeric: Numeric[T]): Sub[Rpn[T]] =
    case Stack(top1, Stack(top2, rest)) =>
      Stack(numeric.minus(top1, top2), rest)
    case Stack(top1, Empty) => Stack(numeric.negate(top1))
    case Empty              => notEnoughElements

  extension [T: Numeric](rpn: Rpn[T]) def - : Rpn[T] = rpnInstance(rpn)
