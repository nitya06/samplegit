package com.neev.userservice

import grails.transaction.Transactional
import com.neev.domain.User

//this service i used only for validation
class ValidationService {

    
    //verification for the Status of user
    boolean verifyStatus(String email)
    {
        def user = new User()
        user = User.findByEmail(email)
        if(user.status=="ACTIVE")
         {
              return true
         }
         return false
                        
    }
    
    
    //verification of password
    boolean verifyPassword(String email , String password)
    {
        
            def user = new User()
            user = User.findByEmail(email)
            
                if(password)
                {           
                    if(user.password == password )
                    {
                       return true;
                    }
                    
                }
   
        return false
    }
    
    
    
    //verification of Email
    boolean verifyEmail(String email)
    {
        def user = new User()
        try{
        if(email)
        {
            user = User.findByEmail(email)
            if(user)
            {
                return true
            }
           
                else
                        return false;
        }
        }
        catch(Exception e){
        return false;
        }
    }
    
    //verification of token at initial time
    def verifyToken(String token)
    {    
       User user = User.findByVerify_code_for_signIn(token);
       if(user)
       {
                  String status = user.status
                  if( status.equals("ACTIVE") )
                  {
                       return "alreadyVerified";
                  }
                  else
                  {
                       user.setStatus("ACTIVE");
                       user.save();
                       return "verify-success";
                  }
        }
        else
        {
                 return "verify-failure";
        }

       
    }
    
    //verification of forget password token
    def verifyForgetPasswordToken(String passwordToken)
    {
        if(passwordToken)
        {
            User user = new User()
            user = User.findByForgotPassword_token(passwordToken)
            if(user){
                user.setForgotPassword_token(null)
                user.save()
                return user
            }else{
                return null
            }
    
        }
    }
    
    
    // user defined function for retrieving the User object by email-id
    def getUserByEmail(String email){
        
        User u = User.findByEmail(email.trim())
        println u
        if(u){
            return u
        }
        return null;
        
    }
    
    
    

}
