package server

import service.user.UserService._
import service.login.LoginService._
import service.HeartBeatService._
import framework.Configuration._

import cats.implicits._
import org.http4s.implicits._

import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze._
import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends StreamApp[IO] {

  println("Starting web server")

  val services = userCorsService <+> loginCorsService <+> heartBeatCorsService

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(serverConfig.port, serverConfig.host)
      .mountService(services, "/")
      .serve
}  
