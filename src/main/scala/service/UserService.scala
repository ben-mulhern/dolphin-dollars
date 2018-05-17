package userServicePackage

import  domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._

class UserService {

  val UserDal = new UserDal

  def getUserJWT(userID:UserID, userInputtedPassword:String): Either[String,Boolean] = {
    val (dataBasePassword,salt) = UserDal.getPasswordSalt(userID).getOrElse(("",""))

    if (userInputtedPassword.bcrypt(salt) == dataBasePassword) Right(true)
    else Left("Could not make JWT")
  }
}