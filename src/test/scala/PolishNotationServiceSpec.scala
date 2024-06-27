import org.scalatest.freespec.AnyFreeSpec

class PolishNotationServiceSpec extends AnyFreeSpec {
  "reversePolishNotation" - {
    "should return expected values for correct polish notations" in {
      val input1 = "4 5 + 6 -"
      val input2 = "4 5 7 * 6 - +"
      val input3 = "4 -5 ABS 7 * 6 - +"
      val input4 = " 4 -5 abs * 6 4 - *"
      val input5 = " 4 5 7 MAX -2 /"
      val input6 = " 4 5 +   -2 +"
      val input7 = " 1 0 /"
      val input8 = " 1 MAX MAX MAX"

      assert(PolishNotationService.reversePolishNotation(input1, " ") == Right(3))
      assert(PolishNotationService.reversePolishNotation(input2, " ") == Right(33))
      assert(PolishNotationService.reversePolishNotation(input3, " ") == Right(33))
      assert(PolishNotationService.reversePolishNotation(input4, " ") == Right(40))
      assert(PolishNotationService.reversePolishNotation(input5, " ") == Right(-3))
      assert(PolishNotationService.reversePolishNotation(input6, " ") == Right(7))
      assert(PolishNotationService.reversePolishNotation(input7, " ") == Left(s"Incorrect operation in expression [$input7]: Can't divide by 0"))
      assert(PolishNotationService.reversePolishNotation(input8, " ") == Right(1))
    }
    "should return string with error description for incorrect polish notations" in {
      val input1 = "4 + 6 -"
      val input2 = " 4 5 + 6 "
      val input3 = " 4 5 plus -2 /"
      val input4 = " 4 5 +  -2 + *"
      val input5 = "max 2 5 + -2 +"
      val input6 = "abs 2 5 + -2 +"
      val input7 = "- 2 5 + -2 +"
      val input8 = "max"

      assert(PolishNotationService.reversePolishNotation(input1, " ") == Left(s"Expression [$input1] is not a correct polish notation," +
        s" because of: Not enough elements for operator [+] in List(4)"))
      assert(PolishNotationService.reversePolishNotation(input2, " ") == Left(s"Expression [$input2] is not a correct polish notation"))
      assert(PolishNotationService.reversePolishNotation(input3, " ") == Left(s"Expression [$input3] is not a correct polish notation," +
        s" because of: Element [plus] is not expected in polish notations"))
      assert(PolishNotationService.reversePolishNotation(input4, " ") == Left(s"Expression [$input4] is not a correct polish notation," +
        s" because of: Not enough elements for operator [*] in List(7)"))
      assert(PolishNotationService.reversePolishNotation(input5, " ") == Left(s"Expression [$input5] is not a correct polish notation," +
        s" because of: Not enough elements for operator [max] in List()"))
      assert(PolishNotationService.reversePolishNotation(input6, " ") == Left(s"Expression [$input6] is not a correct polish notation," +
        s" because of: Not enough elements for operator [abs] in List()"))
      assert(PolishNotationService.reversePolishNotation(input7, " ") == Left(s"Expression [$input7] is not a correct polish notation," +
        s" because of: Not enough elements for operator [-] in List()"))
      assert(PolishNotationService.reversePolishNotation(input8, " ") == Left(s"Expression [$input8] is not a correct polish notation," +
        s" because of: Not enough elements for operator [max] in List()"))
    }
  }
}
