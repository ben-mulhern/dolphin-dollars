package service

import  domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._
import service.token.TokenService._

object UserService {

  val UserDal = new UserDal

  def getUserJWT(userID:UserID, userInputtedPassword:String): Either[String,Jwt] = {
    val (databasePassword,salt) = UserDal.getPasswordSalt(userID).getOrElse(("",""))
    val hashedUserInputPassword = userInputtedPassword.bcrypt(salt)
    checkAndCreateToken(userID,hashedUserInputPassword,databasePassword)
  }

  def checkAndCreateToken(userID: UserID, userInputPassword: String, databasePassword: String): Either[String,Jwt] = {
    if (userInputPassword == databasePassword) Right(createToken(userID))
    else Left("Could not make JWT")
  }
}