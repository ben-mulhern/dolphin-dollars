package framework

object EitherUtilities {

  def eitherTest(condition: Boolean, failMessage: String): Either[String, Unit] = 
    if (condition) Right(Unit)
    else Left(failMessage)

}