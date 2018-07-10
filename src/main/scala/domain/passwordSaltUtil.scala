package domain

import com.github.t3hnar.bcrypt._
import domain.User.UserID
import service.token.TokenService.{Jwt, createToken}

object passwordSaltUtil {

  val checkAndCreateTokenFailureMessage: String = "Invalid username or password"

  def getHashedPassword(userInputtedPassword: String, salt: String): Either[String, String] = {
    val hashedPassword: String = userInputtedPassword.bcrypt(salt)
    if (hashedPassword.isEmpty) Left("Hashing process failed.")
    else Right(hashedPassword)
  }

  def getRandomSalt: Either[String, String] = {
    val result: String = generateSalt
    if (result.isEmpty) Left("Could not generate salt")
    else Right(result)
  }

  def checkAndCreateToken(userID: UserID, hashedUserInputPassword: String, databasePassword: String): Either[String,Jwt] = {
    if (hashedUserInputPassword == databasePassword) Right(createToken(userID))
    else Left(checkAndCreateTokenFailureMessage)
  }

}
