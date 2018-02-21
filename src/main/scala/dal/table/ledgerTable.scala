package dal.table

import sqlest._

class LedgerTable(alias: Option[String]) extends Table(ledger) {

  val ledgerID = column[String]("ledger_id")
  val description = column[String]("description")

}

object LedgerTable extends LedgerTable(None)