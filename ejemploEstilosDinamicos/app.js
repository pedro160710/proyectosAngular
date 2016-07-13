
var app = angular.module("aplicacion", []);

// toda funcionalidad forma parte de un modulo
	
app.controller("appCtrl",['$scope',function($scope){
	 $scope.clasesCss={
    rojo:true,
    subrayado:true,
    negrita:false
  };
  $scope.a="valor AAA";

  $scope.isDisabled=false;
  $scope.desactivar =function(){
  	$scope.isDisabled=true;
  };
}]);