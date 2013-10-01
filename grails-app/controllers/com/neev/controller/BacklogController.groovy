package com.neev.controller
import groovy.util.logging.Slf4j
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.domain.*
import com.neev.mainservice.BacklogInfoService

 @Slf4j
class BacklogController {

    JsonBuilder builder = new JsonBuilder();
    def backlogInfoService
    
    
    def index() 
    {
       
    }
    
    //save the backlog for specific user
    def add()
    {
        log.info"entered into add method of Backlog Controller "
       def json = request.JSON
       def user = backlogInfoService.verifySessionToken(json.token)
       if(user)
       {
           if(backlogInfoService.isHasProject(user , json))
           {
               
                if( backlogInfoService.add(json , user) )
                {
                  println "your data is save"
                  builder.response(status:"user authentication success and Data Save",code:"200");    
                  render builder.toString();
                }
                
                
           }
           else
           {
               builder.response(status:"this user doesnt have this project",code:"500");    
               render builder.toString();
           }
           
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    
    
    
    
    
    
     //update the data for specipic backlog
    def update()
    {
        log.info"entered into update method of Backlog Controller "
       def json = request.JSON
       def user = backlogInfoService.verifySessionToken(json.token)
      
       if(user)
       {
           
           if(backlogInfoService.isHasBacklog(user , json))
           {
               
               if( backlogInfoService.update(json) )
               {
                  println "your data is upadte"
                  builder.response(status:"user authentication success and Data update",code:"200");    
                  render builder.toString();
               }
           }
           else
           {
               builder.response(status:"user doesnt have this backlog",code:"300");    
               render builder.toString();
           }          
           
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    
    
    
    //get the all backlog for specific user
    def get()
    {
        log.info"entered into get method of Backlog Controller "
       def json = request.JSON
       def user = backlogInfoService.verifySessionToken(json.token)
       if(user)
       {
           if(backlogInfoService.isHasProject(user , json))
           {
                    
               List list = backlogInfoService.get(json) 
               println list
               builder.response(status:"user authentication success and fetch the data into console",code:"200");    
               render builder.toString();
                            
           }
           else
           {
               builder.response(status:"this user doesnt have this project",code:"500");    
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
