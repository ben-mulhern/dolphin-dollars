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

object UserService {

  val UserDal = new UserDal {}

  val userService = HttpService {
    case req @ POST -> Root / "userPassword"  =>
      req.decode[String] { data =>
        //logger.info("Received request to create new user profile: " + data)
        val up = read[UserPassword](data)
        httpJsonResponse(UserDal.createUser(up.user, up.password))
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
      println(s"Retrieved player $p from database")
      httpJsonResponse(p)

  }



}

