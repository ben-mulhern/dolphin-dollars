package service

import domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._
import service.token.TokenService._

object UserService {

  val UserDal = new UserDal
  val checkAndCreateTokenFailureMessage: String = "Could not make JWT"

  def getPasswordAndSalt(userID:UserID): Either[String,(String,String)] = {
    val extract: Option[(String,String)] = UserDal.getPasswordSalt(userID)
    if(extract.isEmpty) Left("User ID or password incorrect")
    else Right(extract.get)
  }

  def getHashedPassword(userInputtedPassword:String,salt:String): Either[String,String] = {
    val hashedPassword: String = userInputtedPassword.bcrypt(salt)
    if(hashedPassword.isEmpty) Left("hashing Process Failed")
    else Right(hashedPassword)
  }

  def checkAndCreateToken(userID: UserID, userInputPassword: String, databasePassword: String): Either[String,Jwt] = {
    if (userInputPassword == databasePassword) Right(createToken(userID))
    else Left(checkAndCreateTokenFailureMessage)
  }

  def getUserJWT(userID:UserID, userInputtedPassword:String): Either[String,Jwt] = for {
    passwordSalt <- getPasswordAndSalt(userID)
    hashedUserInputPassword <- getHashedPassword(userInputtedPassword,passwordSalt._2)
    finalJwt<- checkAndCreateToken(userID, hashedUserInputPassword, passwordSalt._1)
  } yield finalJwt

}