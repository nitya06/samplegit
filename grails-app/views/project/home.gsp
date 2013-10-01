<!DOCTYPE html>
<html ng-app>
<head>
  <title>My new webpage</title>
  
   <script src="js/jquery-2.0.3.js"></script>
<script src="js/bootstrap.min.js"></script>

 <!--linking with angular js file -->
<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
        
 <!--linking with separate iceScrum.js file -->
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
   <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.css">
    <!--linking with separate iceScrum.css file -->    
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css">

<script type="text/javascript">
  
 
$(document).ready(function(){
  $("#bar1").click(function(){
    
    $("#div2").hide().delay( 1300 );
    $("#div1").show("slow");
    
  });
});
$(document).ready(function(){
  $("#bar2").click(function(){
    $("#div1").hide().delay( 1300 );    
    $("#div2").show("slow");

  });
});
</script>



<script src="${resource(dir: 'js', file: 'project.js')}"></script>
</head>


<body>
  
<div class="wrapper">
 <div class="container pull-right" style="margin-right:-1.2%">
<ul class="menu" >
<li id="bar1"><a ><button type="button" class="btn btn-link" style="color:#1CE62A;">Home</button></a></li>
<!-- <li id="bar2"><a href="#"><button type="button" class="btn btn-link" style="color:#1CE62A;" onclick="#account">My Account</button></a></li> -->
<li id="bar3"><a href="http://localhost:8080/iceScrum/users/signOut"><button type="button" class="btn btn-link" style="color:#1CE62A;">Log Out</button></a></li>
</ul>
</div>
</div>
<div class="container">
  <div class="col-md-3" style="margin-top:5%;margin-left:-4%">
<nav>
    <div class="menu-item alpha">
      <h4><a href="#">Home</a></h4>
      <p>Welcome To The Profile Page.Have fun.</p>
    </div>
       
    <div class="menu-item" id="menu-item-2">
      <h4><a href="#">Projects</a></h4>
      <ul>
     
         <li id="bar2"><a href="#"><button type="button" class="btn btn-link" style="color:#1CE62A;" onclick="#account">New Project</button></a></li>
         
     
      <!--  <li id="bar2"><a href="#">New Project</a></li> -->
        <li><a href="#">View Projects</a></li>
        <li><a href="#">Assigned Projects</a></li>
        <li><a href="#">Search</a></li>
      </ul>
    </div>
       
    <div class="menu-item" id="menu-item-3">
      <h4><a href="#">Contact</a></h4>
      <ul>
        <li><a href="#">Phone</a></li>
        <li><a href="#">Email</a></li>
        <li><a href="#">Location</a></li>
      </ul>
    </div>
</nav>
</div>

<div class="col-md-9 body-boder" style="margin-top:9.3%;margin-left:0%">
  <div id="div1">
  <h1 align="center" style="color:#289EF2">Body Part</h1>
  <p> Its a humble request to write your all your code in this tag only. Don't Create unnecessary div every time. For further Assistance please Contact UI/UX Developer</p>
</div>
<div id = "div2">
  <div class="container" >
    <ul class="list-inline ">
      <li class="col-md-6">
      </li>
<li class="col-md-6">
  
<h3 align="center" style="color:#289EF2">New Project</h3>

<div ng-controller="CreateCtrl" class="container">
      <input type="hidden" name="sessionToken" value="${sessionToken}"/>
      <ul class="list-inline">
        <li class="col-md-6">
          <p style="margin-top:5%">Project Name</p>
          <p style="margin-top:14%">Project Description</p>
          <p style="margin-top:18%">Project Status</p>
        </li>
        <li class="col-md-6">
      <g:form ng-submit="save()">
      
      <p style="margin-left:-3%"><input type="text" ng-model="name" class="form-control" placeholder="Project Name"></p>
      <p style="margin-left:-3%"><input type="text" ng-model="description" class="form-control" placeholder="Project description"></p>
      <p style="margin-left:-3%"><input type="text" ng-model="status" class="form-control" placeholder="Project Status"></p>
      <p class="submit"><input type="submit" class="btn btn-primary pull-right" value="save"></p>
     </g:form>
   </li>
 </ul>
</div>
  </li>
</ul>
</div>
<hr/>

</div>
</div>
</body>