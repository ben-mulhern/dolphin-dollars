package dal

import sqlest._
import domain.User._
import dal.table.UserDetailTable
import service.token.TokenService.{Jwt, createToken}


class PasswordSaltDal extends SqlestDb {
  val UserDal = new UserDal
  val checkAndCreateTokenFailureMessage: String = "Invalid username or password"

  def getPasswordSaltSQL(id: UserID): Option[(String,String)]  = {
    select(UserDetailTable.password, UserDetailTable.salt)
      .from(UserDetailTable)
      .where(UserDetailTable.userID === id)
      .fetchHeadOption
  }


  def getPasswordAndSalt(userID:UserID): Either[String,(String,String)] = {
    val extract: Option[(String,String)] = getPasswordSaltSQL(userID)
    if(extract.isEmpty) Left("User ID or password incorrect")
    else Right(extract.get)
  }




  def checkAndCreateToken(userID: UserID, hashedUserInputPassword: String, databasePassword: String): Either[String,Jwt] = {
    if (hashedUserInputPassword == databasePassword) Right(createToken(userID))
    else Left(checkAndCreateTokenFailureMessage)
  }

  def getUserJWT(userID:UserID, userInputtedPassword:String): Either[String,Jwt] = for {
    passwordSalt <- getPasswordAndSalt(userID)
    hashedUserInputPassword <- UserDal.getHashedPassword(userInputtedPassword,passwordSalt._2)
    finalJwt<- checkAndCreateToken(userID, hashedUserInputPassword, passwordSalt._1)
  } yield finalJwt

}