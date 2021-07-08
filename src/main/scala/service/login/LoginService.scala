package service.login

import domain.User._
import dal._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.middleware._
import framework.Configuration._

package object LoginService {

  val PasswordSaltDal = new PasswordSaltDal
  val loginService = HttpService[IO] {
    case req @ POST -> Root / "loginAttempt" =>
      req.decode[String] { data =>
        val up = read[UserIDPassword](data)
        val jwt = PasswordSaltDal.getUserJWT(up.userID, up.password)
        if (jwt.isRight) {
          val cookie = Cookie("authcookie", jwt.getOrElse(""), 
                              httpOnly = true, secure = serverConfig.https)
          httpJsonResponse(jwt).addCookie(cookie)
        } else httpJsonResponse(jwt)
      }
  }
  val loginCorsService = CORS(loginService, methodConfig)
}

