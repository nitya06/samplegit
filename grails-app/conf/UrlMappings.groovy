class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

       "/project"(controller: "project", parseRequest: true)
        {
            action = [POST: "add" , PUT : "update"]
            // action = [POST: "get"]
        }
        
        "/release"(controller: "release", parseRequest: true)
        {
            action = [POST: "add" , PUT : "update"]
              //action = [POST: "get"]
        }
        
        
        "/sprint"(controller: "sprint", parseRequest: true)
        {
             action = [POST: "add" , PUT : "update"]
           //  action = [POST: "get"]
        }
        
        "/task"(controller: "task", parseRequest: true)
        {
             action = [POST: "add" , PUT : "update"]
          // action = [POST: "get"]
        }
        
        "/backlog"(controller: "backlog", parseRequest: true)
        {
             action = [POST: "add" , PUT : "update"]
            // action = [POST: "get"]
        }
        
        
        
        
       //"/"(view:"/test")
       "/"(view:"/users/LoginPage")
        "500"(view:'/error')
	}
        
    
}
