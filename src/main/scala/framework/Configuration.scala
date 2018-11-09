package framework

import com.typesafe.config.ConfigFactory

case class SecurityConfig(jwtSecret: String, jwtExpirationTime: Long, jwtRefreshTime: Long)
case class Database(url: String, username:String, password:String, port: Int, dbName: String, schema: String)
case class ServerConfig(host: String, port: Int, https: Boolean)


object Configuration {
  private val conf = ConfigFactory.load

  val url: String = conf.getString("appdb.url")
  val username: String = conf.getString("appdb.username")
  val password: String = conf.getString("appdb.password")
  val dbPort: Int = conf.getInt("appdb.port")
  val dbName: String = conf.getString("appdb.databaseName")
  val schema: String = conf.getString("appdb.schema")

  val host: String = conf.getString("appserver.host")
  val port: Int = conf.getInt("appserver.port")
  val https: Boolean = conf.getBoolean("appserver.https")

  val jwtSecret: String = conf.getString("appsecurity.jwtSecret")
  val jwtExpirationTime: Long = BigInt(conf.getString("appsecurity.jwtExpirationTime")).toLong
  val jwtRefreshTime: Long = BigInt(conf.getString("appsecurity.jwtRefreshTime")).toLong

  val securityConfig: SecurityConfig = SecurityConfig(jwtSecret, jwtExpirationTime, jwtRefreshTime)
  val database: Database = Database(url, username, password, dbPort, dbName, schema)
  val serverConfig: ServerConfig = ServerConfig(host, port, https)

}