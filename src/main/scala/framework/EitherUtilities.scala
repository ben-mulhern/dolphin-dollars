package framework

object EitherUtilities {

  def eitherTest(condition: Boolean, failMessage: String): Either[String, Unit] = 
    if (condition) Right(Unit)
    else Left(failMessage)

  def optionToEither[A](opt: Option[A], noneMessage: String): Either[String, A] = 
    if (opt.isEmpty) Left(noneMessage)
    else Right(opt.get)

}