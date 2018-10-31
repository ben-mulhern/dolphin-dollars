package dal

import javax.sql.DataSource

import sqlest._
import com.typesafe.config._
import framework.Configuration
import sqlest.sql.PostgresStatementBuilder

trait SqlestDb {
  val url : String = Configuration.database.url
  val password: String  = Configuration.database.password
  val user : String  = Configuration.database.username
  val dbPort: Int = Configuration.database.port
  val dbName: String = Configuration.database.dbName
  val schema: String = Configuration.database.schema

  val dataSource: DataSource = {
    val dataSource = new org.postgresql.ds.PGSimpleDataSource
    dataSource.setServerName(url)
    dataSource.setDatabaseName(dbName)
    dataSource.setPortNumber(dbPort)
    dataSource.setUser(user)
    dataSource.setPassword(password)
    dataSource.setCurrentSchema(schema)
    dataSource
  }

  val statementBuilder: PostgresStatementBuilder = sqlest.sql.PostgresStatementBuilder

  implicit val database: Database = Database.withDataSource(dataSource, statementBuilder)

  val upper: ScalarFunctionColumn1[String,String] = ScalarFunction1[String, String]("upper")

}