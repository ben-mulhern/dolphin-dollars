import  domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._

object UserDal extends UserDal

class UserService {

  def getUserJWT(userID:UserID, password:String): Either[String,Boolean] = {
    val (dataBasepassword,salt) = UserDal.getPasswordSalt(userID).getOrElse(("",""))

     if ( password.bcrypt(salt) == dataBasepassword) Right(true)
     else Left("Password not correct")
  }
}