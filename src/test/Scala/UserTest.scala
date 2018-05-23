import domain.UserID.UserID
import org.scalatest.FunSuite
import service.UserService._

class UserTest extends FunSuite {

  val user1 = UserID("User1")
  val password1 = "password"
  val password2 = "PASSWORD"

  test("Matching passwords should yield a JWT"){
    //checkAndCreateToken returns an Either[String,Jwt] so if successful
    // we expect the return value to be a Right()
    val returnValue = checkAndCreateToken(user1,password1,password1)
    assert(returnValue.isRight)
  }

  test("Passwords that do not match should yield an error"){
    val returnValue = checkAndCreateToken(user1,password1,password2)
    assert(returnValue.isLeft)
  }
}