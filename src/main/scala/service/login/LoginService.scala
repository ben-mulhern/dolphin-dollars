package service.login

import domain.User._
import dal._
import org.http4s._
import org.http4s.dsl._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._


package object LoginService {
  val PasswordSaltDal = new PasswordSaltDal {}
  val loginService = HttpService {
    case req@POST -> Root / "loginAttempt" =>
      req.decode[String] { data =>
        println(data)
        val up = read[UserIDPassword](data)
        println(up)
        httpJsonResponse(PasswordSaltDal.getUserJWT(up.userID, up.password))
      }
  }

}
