package rpn.operations

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class RemTest extends AnyWordSpec with Matchers:
  "%" when {
    import Rpn.ops.{*, given}

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Int]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn %)
      }
    }

    "given a stack with one element" should {
      "throw UnsupportedOperationException" in {
        val rpn = Stack(1)

        an[UnsupportedOperationException] shouldBe thrownBy(rpn %)
      }
    }

    "given a stack with two elements" should {
      "return the remainder of division of top two elements" in {
        val rpn = <>.~(16).~(5)

        val remResult = rpn %

        remResult should be(Rpn(1))
      }
    }

    "given a stack with more than two elements" should {
      "return the remainder of division of top two elements" in {
        val rpn = <>.~(1).~(3).~(16).~(5)

        val remResult = rpn %

        remResult should be(<>.~(1).~(3).~(1))
      }
    }

    "given 0 denominator" should {
      "throw ArithmeticException" in {
        val rpn = <>.~(16).~(0)

        an[ArithmeticException] shouldBe thrownBy(rpn %)
      }
    }
  }
