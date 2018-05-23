import domain.UserID.UserID
import org.scalatest.FlatSpec
import service.UserService._
import org.scalatest.Matchers._

class UserTest extends FlatSpec {

  val user1 = UserID("User1")
  val emptyUser = UserID("")
  val password1 = "password"
  val password2 = "PASSWORD"

  "Matching passwords" should "yield a JWT" in {
    //checkAndCreateToken returns an Either[String,Jwt] so if successful
    // we expect the return value to be a Right()
    checkAndCreateToken(user1,password1,password1) should be ('right)
  }

  "Passwords that do not match" should "produce a failure scenario" in{
    checkAndCreateToken(user1,password1,password2) should be ('left)
  }

  "An empty user ID" should "fail to retrieve the password and Salt" in{
    getPasswordAndSalt(emptyUser) should be ('left)
  }
}