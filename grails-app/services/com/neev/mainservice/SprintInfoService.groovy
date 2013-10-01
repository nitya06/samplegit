package com.neev.mainservice
import groovy.util.logging.Slf4j
import grails.transaction.Transactional
import com.neev.domain.Sprint
import com.neev.domain.User
import com.neev.domain.*

@Transactional
class SprintInfoService {

 
    boolean add(def json , def user)
   {
        log.info"entered into add method of ProjectInfoService "
        Sprint newSprint = new Sprint()
        newSprint.name=json.n
        newSprint.description=json.d
        newSprint.status=json.s
        def release = Release1.findById(json.rid)
        newSprint.release=release
        newSprint.userId= user.id
        if(newSprint.save())
        {
            return true
        }
        else
         return false   
       
   }
   
   boolean update(def json)
   {
        log.info"entered into update method of ProjectInfoService "
       def sprint = Sprint.findById(json.sid)
       sprint.name=json.n
       if(sprint.save())
       return true
       else
        return false
   }
    
    
   def get(def json)
   { 
       log.info"entered into get method of ProjectInfoService "
       def release=Release1.findById(json.rid)
       List list = Sprint.findAllByRelease(release)
       return list
   }
  
    
    //varification all
    
    
    def verifySessionToken(def sessionToken)
    {
         log.info"entered into verifySessionToken method of ProjectInfoService "
        def user = User.findBySession_token(sessionToken)
        if(user)
        {
            return user
        }
        return null
    }
    
    
    boolean isHasRelease(def user , def json)
    {  
        log.info"entered into isHasRelease method of ProjectInfoService "
        def release = Release1.findById(json.rid)
        
        if(release.userId==user.id)
        {
            return true
        }
        
         return false
    }
   
    boolean isHasSprint(def user,def json)
    {
        log.info"entered into isHasSprint method of ProjectInfoService "
        def sprint = Sprint.findById(json.sid)
        
        if(sprint.userId==user.id)
        {
            return true
        }
        
         return false
    }
    
    
    
    
}
