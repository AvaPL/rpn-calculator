package example

import rpn.Rpn.ops.{*, given}

@main def main(): Unit =
  // 1 2 3 + - 3 / 10 * 3 % -

  val dotSyntax: Int = <>.~(1).~(2).~(3).+.-.~(3)./.~(10).*.~(3).%.-

  println(dotSyntax)

  import scala.language.postfixOps
  val postfixSyntax: Int = (((((<> ~ 1 ~ 2 ~ 3 +) -) ~ 3 /) ~ 10 *) ~ 3 %) -

  println(postfixSyntax)

  val mixedSyntax: Int = (<> ~ 1 ~ 2 ~ 3 +).-.~(3)./.~(10).*.~(3).%.-

  println(mixedSyntax)
