var app = angular.module("moduloAplicacion");
// aqui colocar lal logica de invocacion a servicios REST y cargar valores al controlador
app.service("adminLibroSrv",function($http){
	this.buscarLibro= function(tituloLibro,exito,error){
		$http.get("http://localhost:8080/CrudRestWeb1/rest/crudLibroRest/consultarLibroTitulo?varTitulo="+tituloLibro)
		.then(
				function(response){
					console.log("libros escontrados"+response.data);
					exito(response.data);
				},function(response){
					error(response.statusText);
				}

			);
		console.log('funcion buscar Libro**');
	};

// metodo  editarLibro
	this.actualizarLibro= function(libro, exito, error){
		$http({
			method:'POST',
			url:"http://localhost:8080/CrudRestWeb1/rest/crudLibroRest/actualizarLibro",
			data: libro,
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			}).then(

				function(response){
					console.log("libro actualizado"+response.data);
					exito(response.data);
				},function(response){
					error(response.statusText);
				}
		
		);
		
		
	};


});
