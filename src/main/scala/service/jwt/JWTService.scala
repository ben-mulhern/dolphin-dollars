package service.jwt

import dal.PasswordSaltDal
import domain.User.UserID
import service.token.TokenService._
import dal.UserDal

package object JWTService {

  val PasswordSaltDal = new PasswordSaltDal
  val UserDal = new UserDal

  val checkAndCreateTokenFailureMessage: String = "Invalid username or password"

  def checkAndCreateToken(userID: UserID, hashedUserInputPassword: String, databasePassword: String): Either[String,Jwt] = {
    if (hashedUserInputPassword == databasePassword) Right(createToken(userID))
    else Left(checkAndCreateTokenFailureMessage)
  }

  def getUserJWT(userID:UserID, userInputtedPassword:String): Either[String,Jwt] = for {
    passwordSalt <- PasswordSaltDal.getPasswordAndSalt(userID)
    hashedUserInputPassword <- UserDal.getHashedPassword(userInputtedPassword,passwordSalt._2)
    finalJwt<- checkAndCreateToken(userID, hashedUserInputPassword, passwordSalt._1)
  } yield finalJwt
}
