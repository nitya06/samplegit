package com.neev.mainservice
import groovy.util.logging.Slf4j
import grails.transaction.Transactional
import com.neev.domain.Project
import com.neev.domain.User

@Transactional
class ProjectInfoService {

    
   boolean add(def json , def user)
   {
       log.info"entered into add method of ProjectInfoService "
        Project newProject = new Project()
        newProject.name=json.n
        newProject.description=json.d
        newProject.status=json.s
        newProject.user=user
        if(newProject.save())
         return true
         else
         return false   
       
   }
   
    
    
   def get(def user)
   {  
       
       log.info"entered into get method of ProjectInfoService  "
       List list = Project.findAllByUser(user)
       return list
   }
   
    
    
   boolean update(def json)
   {     
       
       log.info"entered into update method of ProjectInfoService  "
       def project = Project.findById(json.pid)
       project.name=json.n
       if(project.save())
        return true 
        else
        return false
       
   }
   
    
    
    
    // all Verification for Project ------------------------------->
    
    def verifySessionToken(def sessionToken)
    {
        
       log.info"entered into verifySessionToken method of ProjectInfoService  "
        def user = User.findBySession_token(sessionToken)
        if(user)
        {
            return user
        }
        return null
    }
    
    
    boolean isHasProject(def user , def json)
    {
        log.info"entered into isHasProject method of ProjectInfoService  "
        def pid = json.pid
        def project = Project.findById(pid)
        if(project.user==user)
        {
            return true
        }
        
         return false
    }
    
    
    
}
