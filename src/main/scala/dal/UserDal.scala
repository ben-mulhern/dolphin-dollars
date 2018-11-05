package dal

import sqlest._
import domain.User._
import domain.passwordSaltUtil._
import dal.table.UserDetailTable
import service.token.TokenService.Jwt


object UserDal extends SqlestDb {

	def createUserSQL(user: User, hashedPassword: String, salt: String): Int = {

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
		}}

	def tryCreateUserRecord(user: User, hashedPassword: String, salt: String, requestingUser: User): Either[String, String] = {
		//If we are creating an admin then the requesting user must be an admin
		if (user.admin && !requestingUser.admin) Left("Permission denied!")
		else {
			val result: Int = createUserSQL(user, hashedPassword, salt)
			if (result == 1) Right("User created successfully")
			else Left("Failed to add user to database")
		}
	}

	def createUser(user: User, password: String, requestingUser:User) : Either[String, String] = for {
		salt <- getRandomSalt
		hashedPassword <- getHashedPassword(password, salt)
		tableWriteResult <- tryCreateUserRecord(user, hashedPassword, salt, requestingUser)
	} yield tableWriteResult


  def getUserSQL(userID: UserID): Option[User] = {

    select
      .from(UserDetailTable)
      .where(UserDetailTable.userID === userID)
      .extractHeadOption(UserExtractor.userExtractor)
  }

  def tryReadUserRecord(userID:UserID): Either[String,User] = {
    val result: Option[User] = getUserSQL(userID)
    if(result.isEmpty) Left(s"Could not find $userID")
    else Right(result.get)
  }

	def getUser(userID: UserID): Either[String, User] = for {
    tableReadResult <- tryReadUserRecord(userID)
  } yield tableReadResult

	def isAdmin(userID: UserID): Boolean = getUser(userID).getOrElse(false) == true

	def getRequestingUser(token:Jwt) : User = {
		val userId = service.token.TokenService.getUserFromToken(token).get
		val user = getUser(userId)
		user.getOrElse(User(UserID("BAD USER"),"BAD USER", true, "", false))
	}

//  def updateUserSQL(userID:UserID, newUserInfo:User): Int = {
//    database.withTransaction { implicit transaction =>
//      update(UserDetailTable)
//        .set(UserDetailTable.userID -> newUserInfo.id,
//          UserDetailTable.name -> newUserInfo.name,
//          UserDetailTable.emailAddress -> )
//    }
//	}

//	def tryUpdateUserRecord(userID:UserID, newUserInfo:User): Either[String,String] = {
//		val result = updateUserSQL(userID,newUserInfo)
//	}
//
//	def updateUser(userID: UserID,newUserInfo: User): Either[String,String] = for {
//		userTableDetails <- tryReadUserRecord(userID)
//		tableWriteResult <- tryUpdateUserRecord(userTableDetails.id,newUserInfo)
//	} yield tableWriteResult
}