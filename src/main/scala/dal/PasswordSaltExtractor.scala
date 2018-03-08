package dal

import domain.User._
import sqlest._
import dal.table._


object PasswordSaltExtractor {

  lazy val passwordSaltExtractor = extract[(String,String)](
  	(UserDetailTable.password,UserDetailTable.salt)
  	)
}