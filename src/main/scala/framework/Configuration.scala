package framework

import com.typesafe.config.ConfigFactory

case class SecurityConfig(jwtSecret: String, jwtExpirationTime: Long)
case class Database(url: String, username:String, password:String)
case class Server(host: String, port: Int)


object Configuration {
  private val conf = ConfigFactory.load

  val url: String = conf.getString("appdb.url")
  val username: String = conf.getString("appdb.username")
  val password: String = conf.getString("appdb.password")

  val host: String = conf.getString("appserver.host")
  val port: Int = conf.getInt("appserver.port")

  val jwtSecret: String = conf.getString("appsecurity.jwtSecret")
  val jwtExpirationTime: Long = BigInt(conf.getString("appsecurity.jwtExpirationTime")).toLong

  val database: Database = Database(url,username,password)
  val securityConfig: SecurityConfig = SecurityConfig(jwtSecret,jwtExpirationTime)
  val server: Server = Server(host,port)

}