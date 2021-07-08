package service.user

import domain.User._
import dal._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.middleware._

object UserService {

  val userService = HttpService[IO] {
    case req @ POST -> Root / "userPassword" =>
      req.decode[String] { data =>
        //logger.info("Received request to create new user profile: " + data)
        println("Got here")
        val upt = read[UserPasswordToken](data)
        println(s"user = ${upt.user}")
        println(s"user = ${upt.password}")
        println(s"token = ${upt.token}")
        httpJsonResponse(UserDal.createUser(upt.user, upt.password, UserDal.getRequestingUser(upt.token)))
      }

    case req @ GET -> Root / "helloworld" =>
      httpJsonResponse("Hello world!")

    case GET -> Root / "getUser" / userId =>
      //logger.info(s"Received request for player $playerId")
      //println(s"Received request for player $UserId")
      val p = UserDal.getUser(UserID(userId))
      println(s"Retrieved player $p from database")
      httpJsonResponse(p)

    case GET -> Root / "updateUser" / userId =>
      //logger.info(s"Received request for player $playerId")
      //println(s"Received request for player $UserId")
      val u = UserID(userId)
      val p = UserDal.getUser(u)
      println(s"updated player $p from database")
      httpJsonResponse(p)

    case GET -> Root / "userIdFromToken" / token =>
      val p = service.token.TokenService.getUserFromToken(token)
      println(s"Retrieved user ID $p from token")
      httpJsonResponse(p.toString)

    case GET -> Root / "hasTokenExpired" / token =>
      val p = service.token.TokenService.getTokenExpired(token)
      p match {
        case true => println("Token has expired")
        case false => println("Token is valid")
      }
      httpJsonResponse(p.toString)
  }

  val userCorsService = CORS(userService, methodConfig)

}

