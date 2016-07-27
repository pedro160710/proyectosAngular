aplicacion.controller('loginCtrl',['$scope','$http','$state','toastr','$rootScope','AutenticacionSrv','$cookies',
    function($scope,$http,$state, toastr,$rootScope,AutenticacionSrv,$cookies){

    console.log('Login');

    //VARIABLES SCOPE
    $scope.datosLoginUsuario = {
        username: null,
		password: null
    };

    // LOGIN
    $scope.loginUsuario = function(){

        AutenticacionSrv
            .login($scope.datosLoginUsuario)
            .then(
                function (respuesta){

        			// Si el usuario existe
                    if(respuesta.data && respuesta.status == 200){
                            
                        $scope.datosLoginUsuario = {
                            username:'',
                            password:''
                        };

                        // Establecer variables globales
                        $rootScope.usuarioLogueado = true;
                        $rootScope.idUsuarioLogueado = respuesta.data.idUsuario;
                        $rootScope.nombreUsuarioLogueado = respuesta.data.usuNombre;

                        //crear cookie para manejar acceso a vistas 
                        $cookies.put('username',respuesta.data.usuNombre);
                        $cookies.put('idUsuario',respuesta.data.idUsuario);
                        //$cookies.username = respuesta.data.usuNombre;

                        // Ir a la vista home
                        toastr.success('Hola '+respuesta.data.usuNombre, 'Bienvenido');
                        $state.go("index");
                    }
                    else{
                    	toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }

                },
                function (error){
                    console.log(error);
                    if(error.status==500){
                    	toastr.error('Error interno en el servidor','Error');
                    }
                    if(error.status==404){
                    	toastr.error('Usuario o password incorrectos','Error');
                    }
                }
            );

    };

}]);

aplicacion.controller('logoutCtrl',['$scope','$http','$state','toastr','$rootScope','AutenticacionSrv','$cookies',
    function($scope,$http,$state, toastr,$rootScope,AutenticacionSrv, $cookies){

    console.log('Logout');

    $scope.logoutUsuario = function(){

        AutenticacionSrv
            .logout()
            .then(
                function logout(respuesta){

                    // Si se desloguea el usuario
                    if(respuesta && respuesta.status == 200){

                        // Establecer variables globales
                        $rootScope.usuarioLogueado = false;
                        $rootScope.nombreUsuarioLogueado = '';
                        $rootScope.idUsuarioLogueado = 0;

                        //borrar cookie
                        //$cookieStore.remove('username');
                        $cookies.remove('username');
                        $cookies.remove('idUsuario');
                        // Ir a la vista home
                        $state.go("index");
                        toastr.success('Has salido del sistema', 'Logout');
                    }
                    else{
                        toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }

                },
                function errorLoginUsuario(error){
                    console.log(error);
                    if(error.status==500 || error.status==404){
                        toastr.error('Error interno en el servidor','Error');
                    }
                }
            );
    };

    
}]);
