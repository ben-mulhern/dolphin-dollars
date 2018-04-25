import  domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._

object UserDal extends UserDal

class UserService {

  def getUserJWT(userID:UserID, password:String): Either[String,Boolean] = {
    val (DataBasepassword,salt) = UserDal.getPasswordSalt(userID).getOrElse(("",""))

     if ( password.bcrypt(salt) == DataBasepassword) Right(true)
     else Left("Password not correct")
  }
}