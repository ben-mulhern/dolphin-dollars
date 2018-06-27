package dal

import javax.sql.DataSource

import sqlest._
import com.typesafe.config._
import framework.Configuration
import sqlest.sql.H2StatementBuilder

trait SqlestDb {
  val url : String = Configuration.database.url
  val password: String  = Configuration.database.password
  val user : String  = Configuration.database.username

  val dataSource: DataSource = {
    val dataSource = new org.h2.jdbcx.JdbcDataSource
    dataSource.setURL(url)
    dataSource.setUser(user)
    dataSource.setPassword(password)
    dataSource
  }

  val statementBuilder: H2StatementBuilder  = sqlest.sql.H2StatementBuilder

  implicit val database: Database = Database.withDataSource(dataSource, statementBuilder)

  val upper: ScalarFunctionColumn1[String,String] = ScalarFunction1[String, String]("upper")

}