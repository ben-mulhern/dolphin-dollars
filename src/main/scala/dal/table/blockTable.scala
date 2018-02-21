package dal.table

import sqlest._
import org.joda.time.LocalDate

class BlockTable(alias: Option[String]) extends Table(block) {
  val blockID = column[Int]("block_id")
  val description = column[String]("description")
  val blockTimeStamp = column[Option[LocalDate]]("time_stamp")
  val effectiveDate = column[Option[LocalDate]]("effective_date")
}

object BlockTable extends BlockTable(None)