package service

import domain.User._
import dal._
import org.http4s._
import org.http4s.dsl._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._
import service.token.TokenService.getHeartBeatUpdate

package object HeartBeatService {
  val HeartBeatService = HttpService {
    case GET -> Root / "heartBeat" / token =>
      httpJsonResponse(getHeartBeatUpdate(token))
  }
}