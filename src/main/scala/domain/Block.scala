package domain

case class BlockId (id: Int)

case class Block (id: BlockId,
                  description: String,
                  timeStamp: String,
                  effectiveDate: String)