package domain

case class BlockId (id: Int)

case class Block (id: option[BlockId],
                  description: String,
                  timeStamp: String,
                  effectiveDate: String,
                  blocks: List[BlockDetail]) //BlockDetail.scala