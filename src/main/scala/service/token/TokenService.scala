package service
package token

import java.time.Instant

import framework.Configuration

import domain.User._
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}


object TokenService {

  type Jwt = String

  def createToken(userID: UserID): Jwt = {
    val claim = JwtClaim(
      expiration = Some(Instant.now.plusSeconds(Configuration.securityConfig.jwtExpirationTime).getEpochSecond),
      issuedAt = Some(Instant.now.getEpochSecond),
      subject = Some(userID.toString)
    )

    val algo = JwtAlgorithm.HS256
    JwtCirce.encode(claim, Configuration.securityConfig.jwtSecret, algo)
  }

  def getUserFromToken(token:Jwt):Option[UserID] = {
    for {claim <- getClaimFromToken(token)
         userString <- claim.subject
    } yield UserID(userString)
  }

  def getExpirationTime(token: Jwt):Option[Long] = {
    for {claim <- getClaimFromToken(token)
         expirationTime <- claim.expiration
    } yield expirationTime
  }

  def getTokenExpired(token: Jwt): Boolean = {
    val expirationTime: Long = getExpirationTime(token).getOrElse(0)
    val now = Instant.now.getEpochSecond
    (expirationTime == 0) || (expirationTime < now)
  }

  def getClaimFromToken(token: Jwt): Option[JwtClaim] = {
    val algo = JwtAlgorithm.HS256
    JwtCirce.decode(token,Configuration.securityConfig.jwtSecret,Seq(algo)).toOption
  }
}
