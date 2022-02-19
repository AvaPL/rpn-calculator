package rpn

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import rpn.{Rpn, Stack}

import scala.language.postfixOps

class RpnTest extends AnyWordSpec with Matchers:
  "ops" should {
    import Rpn.ops.given

    "covert to Rpn" when {
      "given Int" in {
        val value = 5

        val rpn: Rpn[Int] = value

        rpn should be(Stack(5))
      }

      "given Double" in {
        val value = 5.5

        val rpn: Rpn[Double] = value

        rpn should be(Stack(5.5))
      }
    }
  }

  "ops" when {
    import Rpn.ops.given

    "given Rpn" should {
      "convert to Int" in {
        val rpn = Stack(5)

        val value: Int = rpn

        value should be(5)
      }

      "convert to Double" in {
        val rpn = Stack(5.5)

        val value: Double = rpn

        value should be(5.5)
      }
    }

    "given a stack with mutiple elements" should {
      "return only top element" in {
        val rpn = Stack(1, Stack(2, Stack(3)))

        val value: Int = rpn

        value should be(1)
      }
    }

    "given EmptyStack" should {
      "throw NoSuchElementException" in {
        val rpn = Rpn.empty[Int]

        a[NoSuchElementException] shouldBe thrownBy(rpn: Int)
      }
    }
  }

  "apply" when {
    "given multiple elements" should {
      "return stack with leftmost element on top" in {
        val stack = Rpn(1, 2, 3)

        stack should matchPattern { case Stack(1, _) => }
      }
    }
  }

  "+" when {
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
