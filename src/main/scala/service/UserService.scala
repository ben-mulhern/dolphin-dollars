import 
domain.UserID._
dal._

class UserService {

  def getUserJWT(userID:UserID, password:String): Either[String,Boolean] = {
    val extract = getPasswordSalt(userID)
    if (saltedPassword == extracted password) Right(true)
    else Left("Password not correct")
  }
}