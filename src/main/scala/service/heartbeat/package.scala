package service

import domain.User._
import dal._
import org.http4s._
import org.http4s.dsl._
import org.json4s.native.Serialization.read
import service.ServiceUtilities._
import service.token.TokenService.Jwt

package object HeartBeatService {
  val HeartBeatService = HttpService {
    case req@POST -> Root / "heartbeat" =>
      req.decode[String] { data =>
        val JWTToken: Jwt = data
        httpJsonResponse(JWTToken)
      }
  }
}