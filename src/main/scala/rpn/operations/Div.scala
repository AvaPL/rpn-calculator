package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Div[T]:
  def apply(t: T): T

object Div:
  private type FI[T] = Fractional[T] | Integral[T]

  given rpnInstance[T](using fi: FI[T]): Div[Rpn[T]] = {
    case Stack(top1, Stack(top2, rest)) =>
      val result = fi match {
        case f: Fractional[T] => f.div(top2, top1)
        case i: Integral[T]   => i.quot(top2, top1)
      }
      Stack(result, rest)
    case _ => notEnoughElements
  }

  extension [T: FI](rpn: Rpn[T]) def / : Rpn[T] = rpnInstance(rpn)
