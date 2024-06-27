import com.typesafe.scalalogging.Logger

import scala.sys.exit

object Main {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      logger.error("Wrong number of arguments: two arguments are expected. " +
        "First argument with polish notation expression and second one with used separator in the expression")
      exit(1)
    } else {
      val expression = args.head
      val separator = args(1)
      val reversedPN = PolishNotationService.reversePolishNotation(expression, separator)
      logger.info(s"Result of reversing polish notation for [$expression] is: $reversedPN")
    }
  }

  private val logger = Logger[Main.type]
}
