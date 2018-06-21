package dal

import domain.User._
import sqlest._
import dal.table._

object UserExtractor {

  lazy val userExtractor = extract[User](
    id = UserDetailTable.userID,
    name = UserDetailTable.name,
    active = UserDetailTable.active,
    email_address = UserDetailTable.emailAddress
  )
}