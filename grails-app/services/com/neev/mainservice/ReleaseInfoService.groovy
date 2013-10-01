package com.neev.mainservice
import groovy.util.logging.Slf4j
import grails.transaction.Transactional
import com.neev.domain.Release1
import com.neev.domain.Project
import com.neev.domain.User

@Transactional
class ReleaseInfoService {

    boolean add(def json , def user)
   {
         log.info"entered into add method of ReleaseInfoService "
        Release1 newRelease = new Release1()
        newRelease.name=json.n
        newRelease.description=json.d
        newRelease.status=json.s
        def project = Project.findById(json.pid)
        newRelease.project=project
        def temp = user.id
        newRelease.userId= temp
        if(newRelease.save())
        {
            return true
        }
        else
         return false   
       
   }
   
    
    
   def update(def json)
   {    
      log.info"entered into update method of ReleaseInfoService "
       def release = Release1.findById(json.rid)
       release.name=json.n
       release.save()
   
   }
     
    
    
    
   def get(def json)
   {  
       log.info"entered into get method of ReleaseInfoService "
       def project=Project.findById(json.pid)
       List list = Release1.findAllByProject(project)
       return list
   }
    
    //varification all
    
    
    def verifySessionToken(def sessionToken)
    {
        log.info"entered into verifySessionToken method of ReleaseInfoService "
        def user = User.findBySession_token(sessionToken)
        if(user)
        {
            return user
        }
        return null
    }
    
    
    boolean isHasProject(def user , def json)
    {
         log.info"entered into isHasProject method of ReleaseInfoService "
        def pid = json.pid
        def project = Project.findById(pid)
        if(project.user==user)
        {
            return true
        }
        
         return false
    }
    
    
    
    
    def verifyRelease(def json , def user)
    {
        log.info"entered into verifyRelease method of ReleaseInfoService "
        def user_id = user.id
        def rid = json.rid
        def release = Release1.findById(rid)
        if(release.userId == user_id)
         return true
         else
         return false
        
    }
   
}
