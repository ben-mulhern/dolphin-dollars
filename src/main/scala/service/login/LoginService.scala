package service.login

import domain.User._
import dal._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import scala.concurrent.ExecutionContext.Implicits.global
import org.http4s.server.middleware._

package object LoginService {

  val PasswordSaltDal = new PasswordSaltDal {}
  val loginService = HttpService[IO] {
    case req@POST -> Root / "loginAttempt" =>
      req.decode[String] { data =>
        val up = read[UserIDPassword](data)
        val jwt = PasswordSaltDal.getUserJWT(up.userID, up.password)
        if (jwt.isRight)
          httpJsonResponse(jwt).addCookie(Cookie("authcookie", jwt.getOrElse("")))
        else
          httpJsonResponse(jwt)
      }
  }
  val loginCorsService = CORS(loginService, methodConfig)
}

