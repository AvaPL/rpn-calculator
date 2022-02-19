package rpn.operations

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class SubTest extends AnyWordSpec with Matchers:
  "-" when {
    import Sub._

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Int]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn -)
      }
    }

    "given a stack with one element" should {
      "negate the element" in {
        val rpn = Stack(1)

        val minusResult = rpn -

        minusResult should be(Rpn(-1))
      }
    }

    "given a stack with two elements" should {
      "diff top two elements" in {
        val rpn = Rpn(1, 3)

        val minusResult = rpn -

        minusResult should be(Rpn(-2))
      }
    }

    "given a stack with more than two elements" should {
      "diff top two elements" in {
        val rpn = Rpn(1, 3, 5, 7)

        val minusResult = rpn -

        minusResult should be(Rpn(-2, 5, 7))
      }
    }
  }
