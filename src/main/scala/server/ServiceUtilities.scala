package service

import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write
import org.json4s.ext._
import com.typesafe.scalalogging._

import org.http4s.dsl.io._
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