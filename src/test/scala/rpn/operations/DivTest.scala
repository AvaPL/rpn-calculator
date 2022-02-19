package rpn.operations

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class DivTest extends AnyWordSpec with Matchers:
  "integral /" when {
    import Div._

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Int]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn /)
      }
    }

    "given a stack with one element" should {
      "throw UnsupportedOperationException" in {
        val rpn = Stack(1)

        an[UnsupportedOperationException] shouldBe thrownBy(rpn /)
      }
    }

    "given a stack with two elements" should {
      "divide top two elements" in {
        val rpn = Rpn(16, 5)

        val mulResult = rpn /

        mulResult should be(Rpn(3))
      }
    }

    "given a stack with more than two elements" should {
      "divide top two elements" in {
        val rpn = Rpn(16, 5, 3, 1)

        val mulResult = rpn /

        mulResult should be(Rpn(3, 3, 1))
      }
    }

    "given 0 denominator" should {
      "throw ArithmeticException" in {
        val rpn = Rpn(16, 0)

        an[ArithmeticException] shouldBe thrownBy(rpn /)
      }
    }
  }

  "fractional /" when {
    import Div._

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Double]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn /)
      }
    }

    "given a stack with one element" should {
      "throw UnsupportedOperationException" in {
        val rpn = Stack(1.5)

        an[UnsupportedOperationException] shouldBe thrownBy(rpn /)
      }
    }

    "given a stack with two elements" should {
      "divide top two elements" in {
        val rpn = Rpn(25.0, 10)

        val mulResult = rpn /

        mulResult should be(Rpn(2.5))
      }
    }

    "given a stack with more than two elements" should {
      "divide top two elements" in {
        val rpn = Rpn(25.0, 10, 3, 1)

        val mulResult = rpn /

        mulResult should be(Rpn(2.5, 3, 1))
      }
    }

    "given positive nominator and 0 denominator" should {
      "return positive infinity" in {
        val rpn = Rpn(16.0, 0)

        val mulResult = rpn /

        mulResult should be(Rpn(Double.PositiveInfinity))
      }
    }

    "given negative nominator and 0 denominator" should {
      "return negative infinity" in {
        val rpn = Rpn(-16.0, 0)

        val mulResult = rpn /

        mulResult should be(Rpn(Double.NegativeInfinity))
      }
    }
  }
