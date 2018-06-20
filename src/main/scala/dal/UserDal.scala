package dal

import sqlest._
import domain.User._
import dal.table.UserDetailTable
import com.github.t3hnar.bcrypt._

class UserDal extends SqlestDb {


	def getHashedPassword(userInputtedPassword:String,salt:String): Either[String,String] = {
		val hashedPassword: String = userInputtedPassword.bcrypt(salt)
		if(hashedPassword.isEmpty) Left("Hashing process failed.")
		else Right(hashedPassword)}


	def createUserSQL(user:User,hashedPassword:String,salt:String): Int =

		database.withTransaction { implicit transaction =>
		insert
			.into(UserDetailTable)
			.values(
				UserDetailTable.userID -> user.id,
				UserDetailTable.emailAddress -> user.email_address,
				UserDetailTable.name -> user.name,
				UserDetailTable.salt -> salt,
				UserDetailTable.password -> hashedPassword,
				UserDetailTable.active -> user.active
			).execute

			}

  def getRandomSalt: Either[String,String] = {
    val result: String = generateSalt
    if (result.isEmpty) Left("Could not generate salt")
    else Right(result)
  }

  def writeNewUser(user:User,hashedPassword:String,salt:String): Either[String,String] = {
    val result: Int = createUserSQL(user,hashedPassword,salt)
    if(result == 1) Right("User created successfully")
    else Left("Failed to add user to database")
  }

  def createUser(user:User,password:String): Either[String,String] = for {
    salt <- getRandomSalt
    hashedPassword <- getHashedPassword(password,salt)
    tableWriteResult <- writeNewUser(user,hashedPassword,salt)
  } yield tableWriteResult

}