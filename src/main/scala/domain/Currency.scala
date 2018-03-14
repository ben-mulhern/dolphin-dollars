package domain

case class CurrencyCode(code: String)

case class Currency(code: CurrencyCode,
                    symbol: Char,
                    description: String,
                    active: Boolean)