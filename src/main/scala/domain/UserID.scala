package domain.User

case class UserID(id: String) extends AnyVal {
  override def toString = id
}

case class UserIDPassword (userID: UserID, password: String)