package domain

case class UserId(id: String)

case class User( id: UserId,
                 name: String,
                 active: Boolean,
                 email_address: String)