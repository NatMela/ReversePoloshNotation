sealed trait Element

case object EmptyElement extends Element

case object NumericElement extends Element {
  def getNumber(el: String): Int = el.toInt
}

sealed trait UnaryOperator extends Element {
  def unaryFunction(el: Int): Int
}

sealed trait BinaryOperator extends Element {
  def binaryFunction(el1: Int, el2: Int): Int
}

sealed trait AggregateOperator extends Element {
  def aggregateFunction(el: List[Int]): Int
}

case object Addition extends BinaryOperator {
  override def binaryFunction(el1: Int, el2: Int): Int = el2 + el1
}

case object Subtraction extends BinaryOperator {
  override def binaryFunction(el1: Int, el2: Int): Int = el2 - el1
}

case object Multiplication extends BinaryOperator {
  override def binaryFunction(el1: Int, el2: Int): Int = el1 * el2
}

case object Division extends BinaryOperator {
  override def binaryFunction(el1: Int, el2: Int): Int = if (el1 != 0) el2 / el1
  else throw IncorrectOperationException(s"Can't divide by 0")
}

case object Abs extends UnaryOperator {
  override def unaryFunction(el: Int): Int = math.abs(el)
}

case object Max extends AggregateOperator {
  override def aggregateFunction(el: List[Int]): Int =
    if (el.nonEmpty) el.max
    else throw IncorrectStructureException(s"Not enough elements for operator [max] in $el")
}