package domain

import service.token.TokenService.Jwt

case class CurrencyCode(code: String) extends AnyVal

case class Currency(code: String,
                    symbol: String,
                    description: String,
                    active: Boolean)

case class CurrencyToken(currency: Currency, token: Jwt)