import domain.User._
import org.scalatest.FlatSpec
import service.UserService._
import org.scalatest.Matchers._

class UserServiceTest extends FlatSpec{

  val user1 = UserID("User1")
  val emptyUser = UserID("")
  val password1 = "password"
  val password2 = "PASSWORD"


  behavior of "The creation of a JWT"

  it should "yield a JWT when passwords match" in {
    checkAndCreateToken(user1,password1,password1) should be ('right)
  }

  it should s"yield the failure message \'$checkAndCreateTokenFailureMessage\' when passwords do not match" in {
    checkAndCreateToken(user1,password1,password2) should be (Left(checkAndCreateTokenFailureMessage))
  }


  behavior of "An empty user ID"

  it should "yield a failure message when retrieving the password and salt" in{
    getPasswordAndSalt(emptyUser) should be ('left)
  }


}
