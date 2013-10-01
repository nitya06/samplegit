package com.neev.userservice

import grails.transaction.Transactional
import com.neev.domain.User;
import java.security.MessageDigest
import org.springframework.mail.MailSendException

@Transactional
class SaveDataService {

    
    
    // save the data from sign up
    def saveData(String name , String email , String password) {
        
        def Status = false
        User user = new User() 
        user.name = name
        user.email =  email
        user.password = password
        user.status = "INACTIVE"
        def hash = md(email,name)
        user.verify_code_for_signIn= hash
        if(user.validate()) 
        {
 
             try 
             {
                 sendMail
                 {
                  to email
                  subject "Verification Code"
                  body "http://localhost:8080/iceScrum/users/verify?token=${hash}"
                 }
                 user.save()
                 return "vailidData"
                 
             }
             catch(MailSendException e)
             {
                return "msgSendingFail"
             }
                   
        }     
        else
        {    
            println "provide your valid information"
            return "dataNotValid"
            
        } 
       
    }

    
   
    //updating password in case of forget password
    boolean updatePassword(String newPassword,String email)
    {
        def user = User.findByEmail(email)
        if(user)
        {
            
            user.password = newPassword
            if(user.save())
            {
               return true
            }
             return false
        }
    }
    
    def getSessionToken(def email)
    {
        def date = new Date()
        def sessToken =  md(email,date)
        return sessToken
    }
    
    def saveSessionToken(def email , def sessionToken)
    {
        User user = new User()
        user = User.findByEmail(email)
        if(user)
        {
            user.session_token=sessionToken
        }
    }
    
    //hash code generating with md5
     def md(def email,def name){
        def digest = MessageDigest.getInstance("MD5")
        def text = "${email} ${name}"
        def md5hash = new BigInteger(1,digest.digest(text.getBytes())).toString(16).padLeft(32,"0")
        return md5hash
   }
    
        
}
