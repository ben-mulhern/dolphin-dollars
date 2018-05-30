package dal

import sqlest._
import domain.User._
import dal.table.UserDetailTable


class PasswordSaltDal extends SqlestDb {
  def getPasswordSalt(id: UserID): Option[(String,String)]  = {
    select(UserDetailTable.password, UserDetailTable.salt)
      .from(UserDetailTable)
      .where(UserDetailTable.userID.toString == id.toString)
      .fetchHeadOption
  }

  def getPasswordAndSalt(userID:UserID): Either[String,(String,String)] = {
    val extract: Option[(String,String)] = getPasswordSalt(userID)
    if(extract.isEmpty) Left("User ID or password incorrect")
    else Right(extract.get)
  }




}