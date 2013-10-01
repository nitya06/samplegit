package com.neev.domain

class Project {

    String name
    String description
    String status
    Date dateCreated
    Date lastUpdated
    Date endDate
    
    User user
    static hasMany = [ release : Release1 ]
    static belongsTo = [user:User]
    
    static constraints = {
        
        endDate nullable:true
        
    }
}