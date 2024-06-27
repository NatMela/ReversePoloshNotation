case class UnknownElementException(message: String = "")
  extends Exception(message)

case class IncorrectOperationException(message: String = "")
  extends Exception(message)

case class IncorrectStructureException(message: String = "")
  extends Exception(message)
