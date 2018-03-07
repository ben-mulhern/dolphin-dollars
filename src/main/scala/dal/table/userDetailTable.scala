package dal.table

import sqlest._
import domain.UserID._

class UserDetailTable(alias: Option[String]) extends Table("user_detail",None) {

  val userID = column[UserID]("user_ID")
  val name = column[String]("name")
  val salutation = column[String]("salutation")
  val emailAddress = column[String]("email_address")
  val password = column[String]("password")
  val salt = column[String]("salt"),

}

object UserDetailTable extends UserDetailTable(None)