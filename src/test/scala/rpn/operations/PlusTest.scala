package rpn.operations

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class PlusTest extends AnyWordSpec with Matchers:
  "+" when {
    import Rpn.ops._

    "given EmptyStack" should {
      "throw UnsupportedOperationException" in {
        val rpn = Rpn.empty[Int]

        an[UnsupportedOperationException] shouldBe thrownBy(rpn +)
      }
    }

    "given a stack with one element" should {
      "throw UnsupportedOperationException" in {
        val rpn = Stack(1)

        an[UnsupportedOperationException] shouldBe thrownBy(rpn +)
      }
    }

    "given a stack with two elements" should {
      "sum top two elements" in {
        val rpn = Rpn(1, 3)

        val plusResult = rpn +

        plusResult should be(Rpn(4))
      }
    }

    "given a stack with more than two elements" should {
      "sum top two elements" in {
        val rpn = Rpn(1, 3, 5, 7)

        val plusResult = rpn +

        plusResult should be(Rpn(4, 5, 7))
      }
    }
  }
