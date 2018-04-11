package dal

import sqlest._
import com.typesafe.config._
import framework.AppConfig._

trait SqlestDb {

  val dataSource = {
    val dataSource = new org.h2.jdbcx.JdbcDataSource
    dataSource.setURL(url)
    dataSource.setUser(user)
    dataSource.setPassword(password)
    dataSource
  }

  val statementBuilder = sqlest.sql.H2StatementBuilder

  implicit val database = Database.withDataSource(dataSource, statementBuilder)

  val upper = ScalarFunction1[String, String]("upper")

}