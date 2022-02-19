package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Div[T]:
  def apply(t: T): T

object Div:
  object fractional:
    given rpnFractional[T](using fractional: Fractional[T]): Div[Rpn[T]] = {
      case Stack(top1, Stack(top2, rest)) =>
        Stack(fractional.div(top1, top2), rest)
      case _ => notEnoughElements
    }

    extension [T: Fractional](rpn: Rpn[T]) def / : Rpn[T] = rpnFractional(rpn)

  object integral:
    given rpnIntegral[T](using integral: Integral[T]): Div[Rpn[T]] =
      case Stack(top1, Stack(top2, rest)) =>
        Stack(integral.quot(top1, top2), rest)
      case _ => notEnoughElements

    extension [T: Integral](rpn: Rpn[T]) def / : Rpn[T] = rpnIntegral(rpn)
