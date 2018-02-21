package dal.table

import sqlest._

class CurrencyTable(alias: Option[String]) extends Table(currency) {

  val currencyCode = column[String]("currency_code")
  val symbol = column[String]("symbol")
  val description = column[String]("description")
  val active = column[Boolean]("active")
}

object CurrencyTable extends CurrencyTable(None)