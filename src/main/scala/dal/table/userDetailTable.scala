package dal.table

import sqlest._
import domain.User._
import sqlest.ast.TableColumn

class UserDetailTable(alias: Option[String]) extends Table("user_detail",None) {

  val userID = column[UserID]("user_ID")
  val name: TableColumn[String] = column[String]("name")
  val emailAddress: TableColumn[String] = column[String]("email_address")
  val password: TableColumn[String] = column[String]("password")
  val salt: TableColumn[String] = column[String]("salt")
  val active: TableColumn[Boolean] = column[Boolean]("active")(BooleanYNColumnType)
  val admin: TableColumn[Boolean] = column[Boolean](name = "admin")(BooleanYNColumnType)
}

object UserDetailTable extends UserDetailTable(None)