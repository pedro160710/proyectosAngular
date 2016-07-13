var app = angular.module('miApp',[]);

app.controller('controladorApp', function($scope){

	// va a manejar a la vista 
	// va a invocar al modelo
	$scope.nombrePersona = "";
	$scope.saludar=function(){
		$scope.saludo="bienvenido"+ $scope.nombrePersona;
	};


});