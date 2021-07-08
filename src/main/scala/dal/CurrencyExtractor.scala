package dal

import dal.table.CurrencyTable
import domain.Currency
import sqlest._

object CurrencyExtractor {

  lazy val currencyExtractor = extract[Currency](
      code = CurrencyTable.currencyCode,
      symbol = CurrencyTable.symbol,
      description = CurrencyTable.description,
      active = CurrencyTable.active
    )

}
