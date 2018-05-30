package service.user

import com.github.t3hnar.bcrypt._
import dal._
import domain.User._


object UserService {
  val UserDal = new UserDal

  def getRandomSalt: Either[String,String] = {
    val result: String = generateSalt
    if (result.isEmpty) Left("Could not generate salt")
    else Right(result)
  }

  def writeNewUser(user:User,hashedPassword:String,salt:String): Either[String,String] = {
    val result: Int = UserDal.createUser(user,hashedPassword,salt)
    if(result == 1) Right("User created successfully")
    else Left("Failed to add user to database")
  }

  def createUser(user:User,password:String): Either[String,String] = for {
    salt <- getRandomSalt
    hashedPassword <- UserDal.getHashedPassword(password,salt)
    tableWriteResult <- writeNewUser(user,hashedPassword,salt)
  } yield tableWriteResult

}

