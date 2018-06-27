package domain.User

//case class UserId(id: String)

case class User( id: UserID,
                 name: String,
                 active: Boolean,
                 email_address: String)