var app= angular.module('CalculadoraApp',[]);
app.controller('CalcControlador', function($scope){
	$scope.memoria="";
	$scope.display="";
	$scope.resultado="";
	$scope.operacion ="";
	$scope.retornar= function(numero){
		$scope.display=$scope.display+""+numero;
		console.log('display'+$scope.display);
	};


	$scope.calcular = function(signo){

		if($scope.memoria===""){

			$scope.memoria=$scope.display;
			$scope.operacion = signo;
			$scope.display="";
		}
		// else if($scope.memoria!==""){
			
		// }
			
	};
	$scope.resultado = function(){
		if($scope.operacion==='+'){
			$scope.resultado= parseFloat($scope.display)+parseFloat($scope.memoria);
			$scope.display=$scope.resultado;
			}
			$scope.operacion="";
			$scope.resultado="";
			$scope.memoria="";
			console.log('memoria'+$scope.display);
	};

	$scope.eliminarUltimo = function(){

	};
	$scope.limpiarPantalla = function(){

	};

});