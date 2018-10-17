package service.user

import domain.User._
import dal._
import org.http4s._
import org.http4s.dsl._
import org.http4s.server._
import org.http4s.server.blaze._
import org.json4s._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._
import service.token.TokenService.Jwt

object UserService {

  val UserDal = new UserDal {}

  val userService = HttpService {
    case req @ POST -> Root / "userPassword" =>
      req.decode[String] { data =>
        //logger.info("Received request to create new user profile: " + data)
        val upt = read[UserPasswordToken](data)
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



}

