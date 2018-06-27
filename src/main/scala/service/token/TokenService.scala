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

}
