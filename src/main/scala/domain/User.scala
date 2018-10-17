package domain.User

import service.token.TokenService.Jwt

case class User( id: UserID,
                 name: String,
                 active: Boolean,
                 email_address: String,
                 admin: Boolean) {}

//case class UserPassword (user: User, password: String)


case class UserPasswordToken (user: User, password: String, token: Jwt)