package dal.table

import sqlest._
import org.joda.time.{ LocalDate, DateTime }

class BlockTable(alias: Option[String]) extends Table("block",None) {
  val blockID = column[Int]("block_id")
  val description = column[String]("description")
  val blockTimeStamp = column[DateTime]("time_stamp")
  val effectiveDate = column[LocalDate]("effective_date")
}

object BlockTable extends BlockTable(None)