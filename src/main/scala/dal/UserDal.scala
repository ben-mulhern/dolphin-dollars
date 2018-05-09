package dal

import sqlest._
import domain.UserID._
import dal.table.UserDetailTable

class UserDal extends SqlestDb {


  def getPasswordSalt(id: UserID): Option[(String,String)]  = {
  	select(UserDetailTable.password, UserDetailTable.salt)
    	.from(UserDetailTable)
    	.where(UserDetailTable.userID === id)
    	.fetchHeadOption
  }
}

