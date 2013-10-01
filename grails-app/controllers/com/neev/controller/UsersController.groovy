package com.neev.controller
import groovy.util.logging.Slf4j
import com.neev.domain.User
import com.neev.userservice.ValidationService
import com.neev.userservice.SaveDataService
import com.neev.userservice.SetForgetPasswordService

@Slf4j
class UsersController {

    def saveDataService
    def validationService
    def setForgetPasswordService
    
    
    
    def index() 
    {
        redirect(action:"/users/LoginPage")
    }
    
    
    
    def addData()
    {  
        log.info "entered into the add method of registration page in Users Controller"
        if( User.findByEmail(params.email) )
        {
            flash.message="This user already Registered!!"
            render(view:"/users/LoginPage")
        }
        else
        {
             String status = saveDataService.saveData(params.name , params.email , params.password)
             if(status=="vailidData")
             {
                 flash.message="SignUp Successfully !!"
                 render(view:"/users/LoginPage")
             }
             else if(status=="msgSendingFail")
             {
                 flash.message="Email-Id is Invalid"
                 render(view:"/users/LoginPage")
             }
             else if(status=="dataNotValid")
             {
                 flash.message="Information Invalid"
                 render(view:"/users/LoginPage")
             }
        }
              
    }
    
    
    def verify()
    {
         log.info "entered into the verify method of Users Controller"
        String status = validationService.verifyToken(params.token)
        render(view:"/users/${status}")
    }
    
    
    
    
    def verifySignIn()
    {  log.info"entered into verifySignIn method of Users Controller "
        
       if( validationService.verifyEmail(params.email) )
       {
           if( validationService.verifyPassword( params.email ,params.password) )
           {
               if( validationService.verifyStatus(params.email) )
               {
                   
                   flash.message="You are successfully Login !!!!!"
                   
                     def sessionToken = saveDataService.getSessionToken(params.email)
                     saveDataService.saveSessionToken(params.email , sessionToken)
                     println sessionToken
                     render(view:"/project/home",model: [sessionToken:sessionToken])
                  
               }else
               {
                   flash.message="First verify your account !!!!!"
                   render(view:"/users/LoginPage")
               }
         
           }else
           {
               flash.message="Password is incorrect!!!!!"
               render(view:"/users/LoginPage")
           }
           
       }else
       {
            flash.message="Email-Id is Invalid !!!!!"
            render(view:"/users/LoginPage") 
       }
        
    }
    
  
    def sendEmail()
    {
       log.info"entered into sendEmail method of Users Controller "
        if( validationService.verifyEmail(params.email))
        { 
           setForgetPasswordService.setForgetPasswordToken(params.email)
           flash.message="check your email for reset your password"
           render(view:"/users/LoginPage")
        }
        else
        {
            flash.message="Email-Id is invalid !!"
            render(view:"/users/getEmailForPassword")
        }
       
        
    }
    

   
    def enterEmail()
    {
        log.info"entered into enter email method of Users Controller "
        render(view:"/users/getEmailForPassword")
    }
    
    
    
    
    def newPassword()
    {
        log.info"entered into newpassword method of Users Controller "
        def user = validationService.verifyForgetPasswordToken(params.passwordtoken)
        if(user)
        {
           render(view:"/users/resetPassword",model: [email:user.email])
        }
        else
        {
            render(view:"/users/alreadyVerifiedForPassword")
        }
    }    
    

    def setPassword()
    {
          log.info"entered into setpassword method of Users Controller "
        if(  saveDataService.updatePassword(params.new_password,params.email))
        {
            flash.message="Your password has been changed !!"
            render(view:"/users/LoginPage")
        }
        else
        {
            flash.message="Some internal Error"
            render(view:"/users/LoginPage")
        }  
    }
    
    
     def signOut()
    {
         log.info"entered into signout method of Users Controller "
        println "I am in Signout Controller"
        session.sessionName="null"
        println "Sign out is done"
        flash.message="LogOut Successfully !!!!!!!"
        redirect(action:"LoginPage")
    }
   
    def LoginPage()
    {
        
    }

}


