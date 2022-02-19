package rpn.operations

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class MulTest extends AnyWordSpec with Matchers:
  "*" when {
    import Mul._

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Int]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn.*)
      }
    }

    "given a stack with one element" should {
      "throw UnsupportedOperationException" in {
        val rpn = Stack(1)

        an[UnsupportedOperationException] shouldBe thrownBy(rpn.*)
      }
    }

    "given a stack with two elements" should {
      "multiply top two elements" in {
        val rpn = Rpn(7, 5)

        val mulResult = rpn *

        mulResult should be(Rpn(35))
      }
    }

    "given a stack with more than two elements" should {
      "multiply top two elements" in {
        val rpn = Rpn(7, 5, 3, 1)

        val mulResult = rpn *

        mulResult should be(Rpn(35, 3, 1))
      }
    }
  }
