package dal

import sqlest._
import domain.User._
import dal.table.UserDetailTable

class UserDal extends SqlestDb {


  def getPasswordSalt(id: UserID): Option[(String,String)]  = {
  	select(UserDetailTable.password, UserDetailTable.salt)
    	.from(UserDetailTable)
    	.where(UserDetailTable.userID.toString == id.toString)
    	.fetchHeadOption
  }

	def createUser(user:User,hashedPassword:String): Int =

		database.withTransaction { implicit transaction =>
		insert
			.into(UserDetailTable)
			.values(
				UserDetailTable.userID -> user.id,
				UserDetailTable.emailAddress -> user.email_address,
				UserDetailTable.name -> user.name,
				UserDetailTable.salt -> "",
				UserDetailTable.password -> hashedPassword,
				UserDetailTable.active -> user.active
			).execute

			}

}