package server

import org.http4s.server.blaze.BlazeBuilder
import service.user.UserService._
import service.login.LoginService._
import service.HeartBeatService._
// import service.currencyService.CurrencyService._
import framework.Configuration._

object DolphinDollarsServer extends App {
  println("Starting web server")
  BlazeBuilder.bindHttp(server.port, server.host)
    .mountService(userService)
    .mountService(loginService)
    .mountService(HeartBeatService)
    //.mountService(currencyService)
    .run
    .awaitShutdown()
}