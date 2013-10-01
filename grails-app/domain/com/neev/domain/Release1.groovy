package com.neev.domain

class Release1 {
 
    String name
    String description
    String status
    Date dateCreated
    Date lastUpdated
    Date endDate
    int userId
    
    Project project
    
     static hasMany = [ sprint : Sprint ]
     static belongsTo = [project : Project]
     
    static constraints = {
        endDate nullable:true
    }
}