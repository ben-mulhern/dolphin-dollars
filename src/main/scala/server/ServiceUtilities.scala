package service

import org.http4s.dsl._
import org.http4s.headers._
import org.http4s.MediaType._
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write
import org.json4s.ext._
import com.typesafe.scalalogging._

import cats.implicits._
import org.http4s.implicits._

import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze._
import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import scala.concurrent.ExecutionContext.Implicits.global
import org.http4s.server.middleware._
import scala.concurrent.duration._

object ServiceUtilities extends LazyLogging {

  implicit val formats = Serialization.formats(NoTypeHints) ++ JodaTimeSerializers.all

  def httpJsonResponse(input: AnyRef) = {
    val jsonResult = write(input)
    logger.info(jsonResult)
    Ok(jsonResult)
  }

  val methodConfig = CORSConfig(
    anyOrigin = true,
    anyMethod = true,
    allowCredentials = true,
    maxAge = 1.day.toSeconds)


}