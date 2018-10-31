package service
package token

import java.time.Instant

import framework.Configuration
import domain.User._
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}

import scala.util.{Failure, Success}


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
         userID <- claim.subject
    } yield UserID(userID)
  }

  def getExpirationTime(token: Jwt):Option[Long] = {
    for {claim <- getClaimFromToken(token)
         expirationTime <- claim.expiration
    } yield expirationTime
  }

  def getRefreshTime(token: Jwt):Option[Long] = {
    for {claim <- getClaimFromToken(token)
         refreshTime <- claim.issuedAt
    } yield refreshTime + Configuration.securityConfig.jwtRefreshTime
  }

  def getIssuedAtTime(token: Jwt):Option[Long] = {
    for {claim <- getClaimFromToken(token)
         issuedAtTime <- claim.issuedAt
    } yield issuedAtTime
  }

  def getTokenExpired(token: Jwt): Boolean = {
    val expirationTime: Long = getExpirationTime(token).getOrElse(0)
    val now = Instant.now.getEpochSecond
    (expirationTime == 0) || (expirationTime < now)
  }

  def getTokenNeedsRefresh(token: Jwt): Boolean = {
    val refreshTime: Long = getRefreshTime(token).getOrElse(0)
    val now: Long = Instant.now.getEpochSecond
    (refreshTime == 0) || (refreshTime < now)
  }

  def getClaimFromToken(token: Jwt): Option[JwtClaim] = {
    val algo = JwtAlgorithm.HS256
    JwtCirce.decode(token,Configuration.securityConfig.jwtSecret,Seq(algo)).toOption
  }

  def getHeartBeatUpdate(token: Jwt): Either[String,Jwt] = {
    val hasExpired = getTokenExpired(token)
    val needsRefresh = getTokenNeedsRefresh(token)
    (hasExpired,needsRefresh) match {
      case (false,false) => Right(token)
      case (false,true) => Right(createToken(getUserFromToken(token).get))
      case (true,false) => Left("Token has expired but does not need refreshing. Config is probably incorrect.")
      case (true,true) => Left("Logging off due to inactivity.")
    }
  }
}
