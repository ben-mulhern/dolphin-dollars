package dal.table

import sqlest._

class BlockDetailTable(alias: Option[String]) extends Table("block_detail",None) {

  val blockID = column[Int]("block_id")
  val entryNumber = column[Int]("entry_number")
  val cashValue = column[Double]("cash_value")
  val userID = column[Int]("user_ID")
  val blockDetailDescription = column[String]("block_detail_description")
  val writtenOff = column[Boolean]("written_off")
  val currencyCode = column[String]("currency_code")
  val ledgerID = column[String]("ledger_id")
}

object BlockDetailTable extends BlockTable(None)