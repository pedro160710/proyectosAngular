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

	$scope.getSigno = function(signo){
		if($scope.memoria==="" ){
			$scope.memoria=$scope.display;
			$scope.operacion = signo;
			$scope.display="";
		}
		console.log('memoria: '+$scope.memoria);
		console.log('selecciona signo'+ signo);
			
	};
	$scope.calcularR = function(){
		console.log('entra en resultado');
		if($scope.display===""){
			console.log('ingrese un valor');
			$scope.display="ingrese un valor";
		}else {
			if($scope.operacion==='+'){
				$scope.resultado= parseFloat($scope.display)+parseFloat($scope.memoria);
				$scope.display=$scope.resultado;
				}

			else if($scope.operacion==='-'){
				$scope.resultado= parseFloat($scope.memoria)-parseFloat($scope.display);
				$scope.display=$scope.resultado;
				}
			else if($scope.operacion==='*'){
				$scope.resultado= parseFloat($scope.display)*parseFloat($scope.memoria);
				$scope.display=$scope.resultado;
				}
			else if($scope.operacion==='/'){
				$scope.resultado= parseFloat($scope.memoria)/parseFloat($scope.display);
				$scope.display=$scope.resultado;
				}
				$scope.operacion="";
				$scope.resultado="";
				$scope.memoria="";
				console.log('memoria'+$scope.display);
		}
	};

	$scope.eliminarUltimo = function(){
		var displayF= ''+$scope.display;
		$scope.display = displayF.substring(0,displayF.length-1);
	};
	$scope.limpiarPantalla = function(){
			$scope.display="";
			$scope.operacion="";
			$scope.resultado="";
			$scope.memoria="";
	};

});