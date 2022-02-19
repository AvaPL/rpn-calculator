package rpn.error

private[rpn] def notEnoughElements: Nothing =
  throw new UnsupportedOperationException(
    s"Not enough elements to perform the operation"
  )

private[rpn] def emptyStackEvaluation: Nothing =
  throw new NoSuchElementException("Evaluation on an empty stack")
