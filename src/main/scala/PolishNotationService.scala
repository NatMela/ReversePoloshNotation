import scala.util.{Failure, Success, Try}

object PolishNotationService {
  def reversePolishNotation(input: String, separator: String): Either[String, Int] = {
    val reversedPN =
      Try(input.trim.split(separator).toList.foldLeft(List.empty[Int]) { (stack, element) =>
        (stack, parseElement(element)) match {
          case (x :: y :: restOfStack, op: BinaryOperator) => op.binaryFunction(x, y) :: restOfStack
          case (x :: restOfStack, op: UnaryOperator) => op.unaryFunction(x) :: restOfStack
          case (st, op: AggregateOperator) => List(op.aggregateFunction(st))
          case (st, num: NumericElement.type) => num.getNumber(element) :: st
          case (st, _: EmptyElement.type) => st
          case (st, _) => throw IncorrectStructureException(s"Not enough elements for operator [$element] in $st")
        }
      })

    reversedPN match {
      case Success(pn) =>
        if (pn.length == 1) Right(pn.head)
        else Left(s"Expression [$input] with separator $separator is not a correct polish notation")
      case Failure(ex: IncorrectOperationException) =>
        Left(s"Incorrect operation in expression [$input] with separator $separator: ${ex.getMessage}")
      case Failure(ex) =>
        Left(s"Expression [$input] with separator $separator is not a correct polish notation, because of: ${ex.getMessage}")
    }
  }

  private def parseElement(element: String): Element = element.trim match {
    case "+" => Addition
    case "-" => Subtraction
    case "*" => Multiplication
    case "/" => Division
    case "abs" | "ABS" => Abs
    case "max" | "MAX" => Max
    case el if el.toIntOption.isDefined => NumericElement
    case "" => EmptyElement
    case el => throw UnknownElementException(s"Element [$el] is not expected in polish notations")
  }
}
