var app = angular.module("moduloAplicacion");
// aqui colocar lal logica de invocacion a servicios REST y cargar valores al controlador
app.service("adminLibroSrv",function(){
	this.buscarLibro= function(){
		$http.get("http://localhost:8080/CrudRestWeb1/rest/crudLibroRest/consultarLibros")
		.then(
				function(response){
					$scope.datos= response.data;
					console.log($scope.datos);
				},function(response){
					console.log(response.statusText);
				}

			);
		console.log('funcion buscar Libro**');
	};

});