package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Mul[T]:
  def apply(t: T): T

object Mul:
  given rpnInstance[T](using numeric: Numeric[T]): Mul[Rpn[T]] =
    case Stack(top1, Stack(top2, rest)) =>
      Stack(numeric.times(top2, top1), rest)
    case _ => notEnoughElements

  extension [T: Numeric](rpn: Rpn[T]) def * : Rpn[T] = rpnInstance(rpn)
