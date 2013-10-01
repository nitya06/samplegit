package com.neev.controller
import groovy.util.logging.Slf4j
import groovy.json.JsonBuilder
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.domain.Project
import com.neev.domain.User
import com.neev.mainservice.ReleaseInfoService
import com.neev.userservice.ValidationService

 @Slf4j
class ReleaseController {
    
    JsonBuilder builder = new JsonBuilder();
    def releaseInfoService
    
    
    def index() 
    {
       
    }
    
    def add()
    {
        log.info "entered into add method of Release Controller "
       def json = request.JSON
       def user = releaseInfoService.verifySessionToken(json.token)
       println "kamlesh"
       if(user)
       {
           if(releaseInfoService.isHasProject(user,json))
           { 
                  
                         if( releaseInfoService.add(json,user) )
                         {
                            println "your data is save"
                            builder.response(status:"user authentication success and Data Save",code:"200");    
                            render builder.toString();
                         }
                         
                  
           }
           else
           {
               builder.response(status:"user doesnt have this project",code:"200");    
               render builder.toString();
               
           }
           
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    
    
    
    
    def update()
    {
        log.info"entered into update method of Release Controller "
       def json = request.JSON
       def user = releaseInfoService.verifySessionToken(json.token)
       if(user)
       {
           if(releaseInfoService.verifyRelease(json , user))
           {
               if( releaseInfoService.update(json) )
                 {
                      println "your data is update"
                      builder.response(status:"user authentication success and Data updated",code:"200");    
                      render builder.toString();
                 }
           }
           else
           {
               builder.response(status:"user doesnt have this Release",code:"200");    
               render builder.toString();
           }
                
          
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    
    
    def get()
    {
        log.info"entered into get method of Release Controller "
       def json = request.JSON
       def user = releaseInfoService.verifySessionToken(json.token)
       println "kamlesh"
       if(user)
       {
           if(releaseInfoService.isHasProject(user,json))
           { 
               
               List list = releaseInfoService.get(json) 
               println list
               builder.response(status:"user authentication success and fetch the data into console",code:"200");    
               render builder.toString(); 
                         
                  
           }
           else
           {
               builder.response(status:"user doesnt have this project",code:"200");    
               render builder.toString();
               
           }
          
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
    }
    

    
}
