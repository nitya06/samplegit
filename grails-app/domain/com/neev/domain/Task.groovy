package com.neev.domain

class Task {
    String name
    String status
    Date dateCreated
    Date lastUpdated  
    Date endDate
    
    Sprint sprint
    User user
    Backlog backlog
    
    static belongsTo = [ sprint : Sprint , user:User , backlog : Backlog]
   
    static constraints = {
        endDate nullable:true
    }
}