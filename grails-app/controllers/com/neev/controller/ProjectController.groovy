package com.neev.controller
import groovy.util.logging.Slf4j
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.domain.Project
import com.neev.domain.User
import com.neev.mainservice.ProjectInfoService

 @Slf4j
class ProjectController {

    JsonBuilder builder = new JsonBuilder();
    def projectInfoService
    
    
    def index() 
    {
       
    }
    
    //save the project for specific user
    def add()
    {  log.info"entered into add method of Project Controller "
       def json = request.JSON
       def user = projectInfoService.verifySessionToken(json.token)
       if(user)
       {
           if( projectInfoService.add(json , user) )
           {
               println "your data is save"
               builder.response(status:"user authentication success and Data Save",code:"200");    
               render builder.toString();
            }
            
           
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    
    
    
    //update the data for specipic project
    def update()
    {
       log.info"entered into update method of Project Controller "
       def json = request.JSON
       def user = projectInfoService.verifySessionToken(json.token)
      
       if(user)
       {
           
           if(projectInfoService.isHasProject(user , json))
           {
               
               if( projectInfoService.update(json) )
               {
                  println "your data is upadte"
                  builder.response(status:"user authentication success and Data update",code:"200");    
                  render builder.toString();
               }
           }
           else
           {
               builder.response(status:"user doesnt have this project",code:"300");    
               render builder.toString();
           }          
           
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
        
    }
    
    //get the all project for specific user
    def get()
    {
        log.info"entered into get method of Project Controller "
       def json = request.JSON
       def user = projectInfoService.verifySessionToken(json.token)
       if(user)
       {
               List list = projectInfoService.get(user) 
               println list
               builder.response(status:"user authentication success and fetch the data into console",code:"200");    
               render builder.toString();   
       }
       else
       {
           builder.response(status:"user authentication fail",code:"500");    
           render builder.toString();
       }   
    }
    
    

    
}
