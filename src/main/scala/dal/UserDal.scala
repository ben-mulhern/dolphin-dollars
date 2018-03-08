package dal

import sqlest._
import sqlest.ast._

import domain.UserID._
import dal.table._
import dal.PasswordSaltExtractor._

trait UserDal extends SqlestDb {


  def getPasswordSalt(id: UserID): Option[(String,String)]  = {

  	val query =
      select
        .from(UserDetailTable)
        .where(UserDetailTable.userID == id)

    query.extractHeadOption(PasswordSaltExtractor)
  }
}