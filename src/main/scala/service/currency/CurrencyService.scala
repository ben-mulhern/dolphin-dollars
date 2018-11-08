package service.currency


import domain.User._
import dal._
import domain.{Currency, CurrencyCode, CurrencyToken}
import org.json4s.native.Serialization.read
import service.ServiceUtilities._

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import scala.concurrent.ExecutionContext.Implicits.global
import org.http4s.server.middleware._

object CurrencyService {
 
  val currencyService = HttpService[IO] {
    case req@POST -> Root / "createCurrency" =>
      req.decode[String] { data =>
        val ct = read[CurrencyToken](data)
        httpJsonResponse(CurrencyDal.createCurrency(ct.currency,UserDal.getRequestingUser(ct.token)))
      }

    case req@POST -> Root / "updateCurrency" =>
      req.decode[String] { data =>
        val ct = read[CurrencyToken](data)
        httpJsonResponse(CurrencyDal.updateCurrency(ct.currency,UserDal.getRequestingUser(ct.token)))
      }

    case req@GET -> Root / "getCurrency" / currencyCode =>
      val p = CurrencyDal.getCurrency(currencyCode)
      println(s"Retrieved currency $p from database")
      httpJsonResponse(p)

    case req@POST -> Root / "disableCurrency" =>
      req.decode[String] { data =>
        val ct = read[CurrencyToken](data)
        httpJsonResponse(CurrencyDal.disableCurrency(ct.currency,UserDal.getRequestingUser(ct.token)))
      }

    case req@POST -> Root / "enableCurrency" =>
      req.decode[String] { data =>
        val ct = read[CurrencyToken](data)
        httpJsonResponse(CurrencyDal.enableCurrency(ct.currency,UserDal.getRequestingUser(ct.token)))
      }

  }

  val currencyCorsService = CORS(currencyService, methodConfig)

}

