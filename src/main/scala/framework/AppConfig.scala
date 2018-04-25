package framework

import com.typesafe.config.ConfigFactory

object AppConfig {
  private val conf = ConfigFactory.load

  val url = conf.getString("appdb.url")
  val user = conf.getString("appdb.username")
  val password = conf.getString("appdb.password")

  val host = conf.getString("appserver.host")
  val port = conf.getInt("appserver.port")

  val pepper = conf.getString("appsecurity.pepper")
  val jwtSecret = conf.getString("appsecurity.jwtSecret")

} 