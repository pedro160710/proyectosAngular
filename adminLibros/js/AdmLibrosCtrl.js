var app= angular.module("moduloAplicacion");
app.controller('miControlador', function($scope,adminLibroSrv){
	$scope.valorDeBusqueda="";

// aqui llamar lasfunciones que se van a invocar como callback en el metodo then
	$scope.buscarLibro = function(){
			adminLibroSrv.buscarLibro($scope.valorDeBusqueda, 
					function(librosEncontrados){
				console.log("libros escontrados"+librosEncontrados);
				$scope.libros= response;
			}, function(mensajeError){
				$scope.error= mensajeError;
			});
	};


	// $scope.division=function(varA, varB){
	// 	$q(function(exito, error ){


	// 	});
	// };

});