
function CreateCtrl($scope,$http) {
  $scope.save = function() {
	
        var jsonObject={"name":$scope.name, "description":$scope.description, "status":$scope.status}
        var jsonstr=JSON.stringify(jsonObject)
        $http.post("http://localhost:8080/iceScrum/session/addData?file="+jsonstr).success(function(data,status,headers,config){
                    alert(data.response.code);
	}).error(function(data,status,headers,config){
			alert("Error")
	});
  
  }
}
