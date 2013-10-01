package com.neev.mainservice

import grails.transaction.Transactional
import com.neev.domain.*


@Transactional
class BacklogInfoService {

   boolean add(def json , def user)
   {
        Backlog newBacklog = new Backlog()
        newBacklog.name=json.n
        newBacklog.description=json.d
        def project = Project.findById(json.pid)
        newBacklog.projects = project
        newBacklog.user=user
        if(newBacklog.save())
         return true
         else
         return false   
       
   }
   
   boolean update(def json)
   {     
       def backlog = Backlog.findById(json.bid)
       backlog.name=json.n
       if(backlog.save())
        return true 
        else
        return false
       
   }
   
   def get(def json)
   {
       def project=Project.findById(json.pid)
       List list = Backlog.findAllByProjects(project)
       return list
   }
   
    
    
    //verification
    def verifySessionToken(def sessionToken)
    {
        def user = User.findBySession_token(sessionToken)
        if(user)
        {
            return user
        }
        return null
    }
    
    
    
    boolean isHasProject(def user , def json)
    {
        
        def pid = json.pid
        def project = Project.findById(pid)
        if(project.user==user)
        {
            return true
        }
        
         return false
    }
    
    
    boolean isHasBacklog(def user , def json)
    {
        def bid = json.bid
        def backlog = Backlog.findById(bid)
        if(backlog.user == user)
        {
            return true
        }
        return false
    }
    
}
