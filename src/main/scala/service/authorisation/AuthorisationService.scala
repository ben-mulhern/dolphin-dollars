package service.authorisation

import org.http4s.util.CaseInsensitiveString
import org.http4s._
import domain.User.UserID
import service.token.TokenService.{getHeartBeatUpdate, getUserFromToken, Jwt}
import framework.EitherUtilities._
import dal.UserDal._

object AuthorisationService {

  // Given a request, extract the Jwt from either the cookie (preferred) 
  // or the Authorization header. 
  // Optionally pass in a user id of the "data subject" - 
  //   You wouldn't use this for a generic service like "get currencies"
  //   But you would for one where you're asking for a particular person's
  //   transaction history. The rule is, if the user from the Jwt matches
  //   the data subject - then great - people can see their own data.
  //   Also admin users are allowed to see everything.

  def authorisationCheck(request: Request, user: Option[UserID] = None): Either[String, Unit] = {

    // This bit needs some work
    // val cookieJwt: Jwt = for {
    //   h <- headers.Cookie.from(request.headers).toRight("Cookie parsing error")
    //   c <- header.values.toList.find(_.name == "authcookie").toRight("Couldn't find the authcookie")
    // } yield c.content.toString

    // val jwt = 
    //   if (cookieJwt.nonEmpty) cookieJwt 
    //   else {
    //     val authHeader = request.headers.find(_.name == CaseInsensitiveString("Authorization"))
    //     authHeader.map(_.value.drop(7)).getOrElse("")
    //   }

    val authHeader = request.headers.find(_.name == CaseInsensitiveString("Authorization"))
    val jwt = authHeader.map(_.value.drop(7)).getOrElse("")    

    for {      
      t <- getHeartBeatUpdate(jwt)
      u <- optionToEither(getUserFromToken(t), "Could not find user ID in JWT")    
    } yield eitherTest((user.isEmpty || (user.get == u) || isAdmin(u)), "Requesting user not authorised to this user's data")
  }

}