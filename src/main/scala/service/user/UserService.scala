package service.user

import domain.User.User
import dal._
import org.http4s._
import org.http4s.dsl._
import org.http4s.server._
import org.http4s.server.blaze._
import org.json4s._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._

object UserService {
  case class UserPassword (user: User, password: String)
  val UserDal = new UserDal {}

  val userService = HttpService {
    case req @ POST -> Root / "userPassword"  =>
      req.decode[String] { data =>

        println("here i am")
        println(data)
        //logger.info("Received request to create new user profile: " + data)
        //val u: User = read[User](data)
        //val p: String = read[String](data)
        val up = read[UserPassword](data)
        println(up)
        httpJsonResponse(UserDal.createUser(up.user, up.password))
      }

   case req @ GET -> Root / "helloworld" => 
     httpJsonResponse("Hello world!")

//    case GET -> Root / "player" / userId =>
//      //logger.info(s"Received request for player $playerId")
//      //println(s"Received request for player $UserId")
//      val p = userDal.getPlayer(playerId.toInt)
//      println(s"Retrieved player $p from database")
//      httpJsonResponse(p)

  }



}

