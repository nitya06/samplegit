package com.neev.domain

class Backlog {
     String name
     String description
     Project projects
     User user
     
     static hasMany = [ task : Task ]
     static constraints = {
         
     }
}
