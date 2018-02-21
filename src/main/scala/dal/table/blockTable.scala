package dal.table

import sqlest._

class BlockTable(alias: Option[String]) extends Table(block) {

  val currencyCode = column[String]("currency_code")
  val symbol = column[String]("symbol")
  val description = column[String]("description")
  val active = column[Boolean]("active")

  val blockID = column[Int]("block_id")
  val description = column[String]("description")
  val blockTimeStamp = column[TimeStamp]("time_stamp")
  val effectiveDate = column[Date]("effective_date")
}

object BlockTable extends BlockTable(None)