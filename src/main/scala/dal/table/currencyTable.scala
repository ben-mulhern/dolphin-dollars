package dal.table

import sqlest._
import sqlest.ast.TableColumn

class CurrencyTable(alias: Option[String]) extends Table("currency",None) {

  val currencyCode: TableColumn[String] = column[String]("currency_code")
  val symbol: TableColumn[String] = column[String]("symbol")
  val description: TableColumn[String] = column[String]("description")
  val active: TableColumn[Boolean] = column[Boolean]("active")(BooleanYNColumnType)
}

object CurrencyTable extends CurrencyTable(None)