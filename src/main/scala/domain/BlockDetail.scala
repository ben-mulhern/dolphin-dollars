package domain

case class BlockDetail(id: BlockId,
                       entryNumber: Int,
                       cashValue: Float,
                       userId: UserId,
                       blockDetailDescription: String,
                       writtenOff: Boolean,
                       currencyCode: CurrencyCode,
                       ledgerId: LedgerId)