aplicacion.service('AutenticacionSrv', ['$http', function($http){
	var urlBase = 'http://localhost:8080/ProjectWeb/api/Autenticacion/';

	this.login = function(datosLoginUsuario){

		console.log('llamando a servicio login');
		return $http({
		    		method:'POST',
		            url: urlBase+'login',
		            data: $.param(datosLoginUsuario),
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function loginCorrecto(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorLogin(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

	this.logout = function(){

		console.log('llamando a servicio logout');
		return $http({
		    		method:'POST',
		            url: urlBase+'logout',
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		        })
		        .then(
			        function logoutCorrecto(respuesta){
						console.log(respuesta);
			            return respuesta;
			        },
			        function errorLogout(error){
			        	console.log(error);				            
			            return error;
			        }
			    );
	};

}]);