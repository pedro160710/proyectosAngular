aplicacion.service('ClienteSrv', ['$http', function($http){
	var urlBase = 'http://localhost:8080/ProjectWeb/api/Clientes';

	this.obtenerClientes = function(){

		console.log('llamando a servicio obtenerClientes');
		return $http({
		    		method:'GET',
		            url: urlBase,
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function obtenerClientes(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorObtenerClientes(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

	this.obtenerClientePorId = function(idCliente){

		console.log('llamando a servicio obtenerClientePorId');
		return $http({
		    		method:'GET',
		            url: urlBase+'/'+idCliente,
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function obtenerCliente(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorObtenerCliente(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

	this.crearCliente = function(nuevoCliente){

		console.log('llamando a servicio crearCliente');
		return $http({
		    		method:'POST',
		            url: urlBase,
            		data: $.param(nuevoCliente),		       
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function clienteCreado(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorCrearCliente(error){	
			        	console.log(error);			            
			            return error;
			        }
			    );
	};

	this.editarCliente = function(idCliente, cliente){

		console.log('llamando a servicio editarCliente');
		return $http({
		    		method:'PUT',
		            url: urlBase+'/'+idCliente,
            		data: $.param(cliente),		       
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function clienteEditado(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorEditarCliente(error){	
			        	console.log(error);		            
			            return error;
			        }
			    );
	};

	this.eliminarCliente = function(idCliente){

		console.log('llamando a servicio eliminarCliente');
		return $http({
		    		method:'DELETE',
		            url: urlBase+'/'+idCliente,
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function clienteEditado(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorEditarCliente(error){	
			        	console.log(error);		            
			            return error;
			        }
			    );
	};

	// Metodos personalizados de la api REST
	this.buscarClientesPorNombre = function(nombreClienteBusqueda){

		console.log('llamando a servicio buscarClientesPorNombre');
		return $http({
		    		method:'GET',
		            url: urlBase+'/buscar/nombre/'+nombreClienteBusqueda,
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function obtenerClientesPorNombre(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorObtenerClientesPorNombre(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

	this.obtenerReporteClientes = function(){

		console.log('llamando a servicio obtenerReporteClientes');
		return $http({
		    		method:'GET',
		            url: urlBase+'/reporte',
		            responseType: 'arraybuffer'
		        })
		        .then(
			        function obtenerReporte(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorObtenerReporte(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

}]);