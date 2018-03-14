package domain

case class UserId(id: String)

case class Password(password: String)

case class User( id: UserId,
                 name: String,
                 password: Password,
                 salt: String,
                 active: Boolean,
                 email_address: String)