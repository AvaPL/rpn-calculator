package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Add[T]:
  def apply(t: T): T

object Add:
  given rpnInstance[T](using numeric: Numeric[T]): Add[Rpn[T]] =
    case Stack(top1, Stack(top2, rest)) =>
      Stack(numeric.plus(top2, top1), rest)
    case _ => notEnoughElements

  extension [T: Numeric](rpn: Rpn[T]) def + : Rpn[T] = rpnInstance(rpn)
