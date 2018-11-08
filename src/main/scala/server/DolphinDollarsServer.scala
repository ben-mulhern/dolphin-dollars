package server

import service.user.UserService._
import service.login.LoginService._
import service.HeartBeatService._
import service.currency.CurrencyService._
import framework.Configuration._
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze._
import cats.effect._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.implicits._

object Main extends StreamApp[IO] {

  println("Starting web server")

  val services = userCorsService <+> loginCorsService <+> heartBeatCorsService <+>
                 currencyCorsService

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(serverConfig.port, serverConfig.host)
      .mountService(services, "/")
      .serve
}  
