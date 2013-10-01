package com.neev.userservice

import grails.transaction.Transactional
import com.neev.domain.User
import java.security.MessageDigest
import org.springframework.mail.MailSendException


class SetForgetPasswordService {

    //set the forgetPasswordToken in data base in case of forget password
    def setForgetPasswordToken(String email)
    {
        def user = new User()
        user = User.findByEmail(email)
        def temp = "kamlesh"
        def hash = md(email,temp)
        user.forgotPassword_token= hash
        sendMail
             {
                to email
                subject "Forget Password Verification"
                body "http://localhost:8080/iceScrum/users/newPassword?passwordtoken=${hash}"
               
             }
        if( user.save() )
         return true
         else
         return false
    }
    
    
    
    
    //generating the hash code with md5
    def md(def email, def name){
        def digest = MessageDigest.getInstance("MD5")
        def text = "${email} ${name}"
        def md5hash = new BigInteger(1,digest.digest(text.getBytes())).toString(16).padLeft(32,"0")
        return md5hash
   }
}
