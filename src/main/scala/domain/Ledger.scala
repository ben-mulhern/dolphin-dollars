package domain

case class LedgerId(id: Option[String])

case class Ledger(id: LedgerId,
                  description: String)