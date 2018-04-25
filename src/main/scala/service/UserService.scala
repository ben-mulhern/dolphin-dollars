import  domain.UserID._
import dal._
import com.github.t3hnar.bcrypt._

object UserDal extends UserDal

class UserService {

  def getUserJWT(userID:UserID, password:String): Either[String,Boolean] = {
    val extract = UserDal.getPasswordSalt(userID).getOrElse(("",""))
    val salt = extract._2

     if ( password.bcrypt(salt) == extract._1) Right(true)
     else Left("Password not correct")
  }
}