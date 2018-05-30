package domain

import domain.User.UserID

case class BlockDetail(entryNumber: Int,
                       cashValue: Double,
                       userId: UserID, //'User.scala'
                       blockDetailDescription: String,
                       writtenOff: Boolean,
                       currencyCode: CurrencyCode, //'Currency.scala'
                       ledgerId: LedgerId) //'Ledger.scala'