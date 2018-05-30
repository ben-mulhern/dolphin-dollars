package dal.table

import sqlest._
import sqlest.ast.TableColumn

class LedgerTable(alias: Option[String]) extends Table("ledger",None) {

  val ledgerID: TableColumn[String] = column[String]("ledger_id")
  val description: TableColumn[String] = column[String]("description")

}

object LedgerTable extends LedgerTable(None)