package domain

case class BlockDetail(id: BlockId, //'Block.scala'
                       entryNumber: Int,
                       cashValue: Double,
                       userId: UserId, //'User.scala'
                       blockDetailDescription: String,
                       writtenOff: Boolean,
                       currencyCode: CurrencyCode, //'Currency.scala'
                       ledgerId: LedgerId) //'Ledger.scala'