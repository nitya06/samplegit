package com.neev.controller
import groovy.util.logging.Slf4j
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.domain.Sprint
import com.neev.domain.User
import com.neev.mainservice.SprintInfoService

 @Slf4j
class SprintController {

    JsonBuilder builder = new JsonBuilder();
    def sprintInfoService
    
    
    def index() 
    {
       
    }
    
    def add()
    {
        log.info"entered into add method of Sprint Controller "
       def json = request.JSON
       def user = sprintInfoService.verifySessionToken(json.token)
       if(user)
       {
           
           if(sprintInfoService.isHasRelease(user,json))
           { 
               
                         if( sprintInfoService.add(json,user) )
                         {
                            println "your data is save"
                            builder.response(status:"user authentication success and Data Save",code:"200");    
                            render builder.toString();
                         }
                         
                  
           }
           else
           {
               builder.response(status:"user doesnt have this release",code:"200");    
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
        log.info"entered into update method of Sprint Controller "
       def json = request.JSON
       def user = sprintInfoService.verifySessionToken(json.token)
       if(user)
       {
           
           if(sprintInfoService.isHasSprint(user,json))
           { 
               
                         if( sprintInfoService.update(json) )
                         {
                            println "your data is update"
                            builder.response(status:"user authentication success and Data updated",code:"200");    
                            render builder.toString();
                         }
                         
                  
           }
           else
           {
               builder.response(status:"user doesnt have this sprint",code:"200");    
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
        log.info"entered into get method of Sprint Controller "
       def json = request.JSON
       def user = sprintInfoService.verifySessionToken(json.token)
       if(user)
       {
           
          if(sprintInfoService.isHasRelease(user,json))
           { 
               
               List list = sprintInfoService.get(json) 
               println list
               builder.response(status:"user authentication success and fetch the data into console",code:"200");    
               render builder.toString();
                         
                  
           }
           else
           {
               builder.response(status:"user doesnt have this release",code:"200");    
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
