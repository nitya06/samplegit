package com.neev.domain

class Sprint {
    
    String name
    String description
    String status
    Date dateCreated
    Date lastUpdated  
    Date endDate
    int userId
    
    Release1 release
    
    static hasMany = [ task : Task ]
    static belongsTo = [ release : Release1 ]
    static constraints = {
        endDate nullable:true
    }
}