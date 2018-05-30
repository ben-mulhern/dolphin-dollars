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


	def createUser(user:User,hashedPassword:String,salt:String): Int =

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

}