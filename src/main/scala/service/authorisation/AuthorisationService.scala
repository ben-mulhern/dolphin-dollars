package service.authorisation

import org.http4s.util.CaseInsensitiveString
import domain.User.UserID
import service.token.TokenService.{getHeartBeatUpdate, getUserFromToken, Jwt}
import framework.EitherUtilities._
import dal.UserDal._

import cats.effect._
import org.http4s._

object AuthorisationService {

  // Given a request, extract the Jwt from either the cookie (preferred) 
  // or the Authorization header. 
  // Optionally pass in a user id of the "data subject" - 
  //   You wouldn't use this for a generic service like "get currencies"
  //   But you would for one where you're asking for a particular person's
  //   transaction history. The rule is, if the user from the Jwt matches
  //   the data subject - then great - people can see their own data.
  //   Also admin users are allowed to see everything.

  def authorisationCheck(request: Request[IO], user: Option[UserID] = None): Either[String, Request[IO]] = {

    val cookieJwt = for {
      h <- headers.Cookie.from(request.headers).toRight("Can't find cookie header")
      c <- h.values.toList.find(_.name == "authcookie").toRight("Couldn't find the authcookie")
    } yield c.content

    val jwt = 
      if (cookieJwt.isRight) cookieJwt
      else for {
        h <- headers.Authorization.from(request.headers).toRight("Can't find authorisation header")
      } yield h.value.drop(7)

    for {  
      j <- jwt    
      t <- getHeartBeatUpdate(j)
      u <- getUserFromToken(t).toRight("Could not find user ID in JWT") 
      _ <- eitherTest((user.isEmpty || (user.get == u) || isAdmin(u)), "Requesting user not authorised to this user's data")
    } yield request
  }

}