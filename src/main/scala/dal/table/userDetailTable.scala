package dal.table

import sqlest._

class UserDetailTable(alias: Option[String]) extends Table(user_detail) {

  val userID = column[Int]("user_ID")
  val name = column[String]("name")
  val salutation = column[String]("salutation")
  val emailAddress = column[String]("email_address")

}

object UserDetailTable extends UserDetailTable(None)