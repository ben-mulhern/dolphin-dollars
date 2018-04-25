package domain

case class BlockDetail(entryNumber: Int,
                       cashValue: Double,
                       userId: UserId, //'User.scala'
                       blockDetailDescription: String,
                       writtenOff: Boolean,
                       currencyCode: CurrencyCode, //'Currency.scala'
                       ledgerId: LedgerId) //'Ledger.scala'