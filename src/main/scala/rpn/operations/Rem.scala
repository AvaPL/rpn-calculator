package rpn.operations

import rpn.{Rpn, Stack}
import rpn.error._

private[rpn] trait Rem[T]:
  def apply(t: T): T

object Rem:
  given rpnInstance[T](using integral: Integral[T]): Rem[Rpn[T]] = {
    case Stack(top1, Stack(top2, rest)) =>
      Stack(integral.rem(top1, top2), rest)
    case _ => notEnoughElements
  }

  extension [T: Integral](rpn: Rpn[T]) def % : Rpn[T] = rpnInstance(rpn)
