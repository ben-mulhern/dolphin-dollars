package dal.table

import sqlest._
import domain.User._
import sqlest.ast.TableColumn

class BlockDetailTable(alias: Option[String]) extends Table("block_detail",None) {

  val blockID: TableColumn[Int] = column[Int]("block_id")
  val entryNumber: TableColumn[Int] = column[Int]("entry_number")
  val cashValue: TableColumn[Double] = column[Double]("cash_value")
  val userID: TableColumn[UserID] = column[UserID]("user_ID")
  val blockDetailDescription: TableColumn[String] = column[String]("block_detail_description")
  val writtenOff: TableColumn[Boolean] = column[Boolean]("written_off")
  val currencyCode: TableColumn[String] = column[String]("currency_code")
  val ledgerID: TableColumn[String] = column[String]("ledger_id")
}

object BlockDetailTable extends BlockTable(None)