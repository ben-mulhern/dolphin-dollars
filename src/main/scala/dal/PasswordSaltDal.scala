package dal

import sqlest._
import domain.User._
import dal.table.UserDetailTable


class PasswordSaltDal extends SqlestDb {
  def getPasswordSaltSQL(id: UserID): Option[(String,String)]  = {
    select(UserDetailTable.password, UserDetailTable.salt)
      .from(UserDetailTable)
      .where(UserDetailTable.userID === id)
      .fetchHeadOption
  }


  def getPasswordAndSalt(userID:UserID): Either[String,(String,String)] = {
    val extract: Option[(String,String)] = getPasswordSaltSQL(userID)
    if(extract.isEmpty) Left("User ID or password incorrect")
    else Right(extract.get)
  }




}