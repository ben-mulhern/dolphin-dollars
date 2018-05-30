package dal.table

import sqlest._
import org.joda.time.{DateTime, LocalDate}
import sqlest.ast.TableColumn

class BlockTable(alias: Option[String]) extends Table("block",None) {
  val blockID: TableColumn[Int] = column[Int]("block_id")
  val description: TableColumn[String] = column[String]("description")
  val blockTimeStamp: TableColumn[DateTime] = column[DateTime]("time_stamp")
  val effectiveDate: TableColumn[LocalDate] = column[LocalDate]("effective_date")
}

object BlockTable extends BlockTable(None)