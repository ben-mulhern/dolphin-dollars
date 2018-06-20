package server

import org.http4s.server.blaze.BlazeBuilder
import service.user.UserService._
import framework.Configuration._

object DolphinDollarsServer extends App {
  println("Starting web server")
  BlazeBuilder.bindHttp(server.port, server.host)
    .mountService(userService)
    .run
    .awaitShutdown()
}