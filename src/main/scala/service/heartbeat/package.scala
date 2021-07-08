package service

import service.ServiceUtilities._
import service.token.TokenService.getHeartBeatUpdate

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.middleware._

package object HeartBeatService {
  val heartBeatService = HttpService[IO] {
    case GET -> Root / "heartBeat" / token =>
      httpJsonResponse(getHeartBeatUpdate(token))
  }

  val heartBeatCorsService = CORS(heartBeatService, methodConfig)
}