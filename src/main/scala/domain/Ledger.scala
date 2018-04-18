package domain

case class LedgerId(id: String)

case class Ledger(id: LedgerId,
                  description: String)