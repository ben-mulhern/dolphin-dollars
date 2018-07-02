package domain.User

case class UserID(id: String) extends AnyVal

case class UserIDPassword (userID: UserID, password: String)