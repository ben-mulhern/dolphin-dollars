package dal

import sqlest._
import dal.table.CurrencyTable
import domain.Currency
import domain.User.User

object CurrencyDal extends SqlestDb {

  def createCurrencySQL(currency: Currency): Int = {

    database.withTransaction { implicit transaction =>
      insert
        .into(CurrencyTable)
        .values(
          CurrencyTable.currencyCode -> currency.code,
          CurrencyTable.symbol -> currency.symbol,
          CurrencyTable.description -> currency.description,
          CurrencyTable.active -> currency.active
        ).execute
    }
  }

  def getCurrencySQL(currencyCode: String): Option[Currency] = {
    select
      .from(CurrencyTable)
      .where(CurrencyTable.currencyCode === currencyCode)
      .extractHeadOption(CurrencyExtractor.currencyExtractor)
  }

  def updateCurrencySQL(newCurrencyDetails: Currency): Int = {
    database.withTransaction { implicit transaction =>
      update(CurrencyTable)
        .set(CurrencyTable.symbol -> newCurrencyDetails.symbol,
          CurrencyTable.description -> newCurrencyDetails.description,
          CurrencyTable.active -> newCurrencyDetails.active)
        .where(CurrencyTable.currencyCode === newCurrencyDetails.code
        ).execute
    }
  }

  def doSQL[A](a: A, requestingUser: User, f: A => Int, successMessage: String, failureMessage: String): Either[String, String] = {
    if (!requestingUser.admin) Left("Permission denied")
    else {
      val result: Int = f(a)
      if (result == 1) Right(successMessage)
      else Left(failureMessage)
    }
  }

  def createCurrency(currency: Currency, requestingUser: User): Either[String, String] = {
    doSQL(currency, requestingUser, createCurrencySQL, "Currency created successfully", "Failed to add currency to database")
  }
  def updateCurrency(newCurrency: Currency, requestingUser: User): Either[String, String] = {
    doSQL(newCurrency, requestingUser, updateCurrencySQL, "Currency updated successfully", "Failed to update currency")
  }
  def disableCurrency(currency: Currency, requestingUser: User): Either[String, String] = {
    doSQL(currency.copy(active = false), requestingUser, updateCurrencySQL, "Currency disabled successfully", "Failed to disable currency")
  }
  def enableCurrency(currency: Currency, requestingUser: User): Either[String, String] = {
    doSQL(currency.copy(active = true), requestingUser, updateCurrencySQL, "Currency enabled successfully", "Failed to enable currency")
  }
  def tryReadCurrencyRecord(currencyCode: String): Either[String, Currency] = {
    val result: Option[Currency] = getCurrencySQL(currencyCode)
    if (result.isEmpty) Left(s"Could not find $currencyCode")
    else Right(result.get)
  }
  def getCurrency(currencyCode: String): Either[String, Currency] = tryReadCurrencyRecord(currencyCode)
}