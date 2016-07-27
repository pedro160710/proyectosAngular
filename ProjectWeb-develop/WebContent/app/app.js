

var aplicacion = angular.module('app', ['ui.router','ngResource','toastr','ui.bootstrap','ngCookies']);
aplicacion.config(function ($stateProvider, $urlRouterProvider,$locationProvider) {

    // Rutas
    $stateProvider
		.state('login', {
            url: "/login",
            templateUrl: "routes/usuarios/login.html",
            controller: 'loginCtrl',
            data: {
                requireLogin: false
            }
        })
        .state('clientes', {
            url: "/clientes",
            templateUrl: "routes/clientes/clientes.html",
            controller: 'clientesCtrl',
            data: {
                requireLogin: true
            }
            
        })
        .state('crearCliente', {
            url: "/crearCliente",
            templateUrl: "routes/clientes/crearCliente.html",
            controller: 'crearClienteCtrl',
            data: {
                requireLogin: true
            }
            
        })
        .state('editarCliente', {
            url: "/editarCliente/:idCliente",
            templateUrl: "routes/clientes/editarCliente.html",
            controller: 'editarClienteCtrl',
            data: {
                requireLogin: true
            }
            
        })
        .state('perfilUsuario', {
            url: "/perfilUsuario/:idUsuario",
            templateUrl: "routes/usuarios/perfilUsuario.html",
            controller: 'perfilUsuarioCtrl',
            data: {
                requireLogin: true
            }
            
        })
        .state('index', {
            url: "/index",
            templateUrl: "routes/usuarios/inicio.html",
            data: {
                requireLogin: false
            }
            //controller: 'LoginController'
        });

	$urlRouterProvider.otherwise("/index");

});

aplicacion.run(function($rootScope,$cookies,$state) {
    // si el usuario no tiene cookie
    $rootScope.usuarioLogueado = false;
    $rootScope.nombreUsuarioLogueado = '';
    $rootScope.idUsuarioLogueado = 0;

    // si el usuario recarga la aplicacion pero ya se logueo
    if(typeof($cookies.get('username')) != "undefined"){
        console.log('ya logueado ANGULAR cookies',$cookies.get('username'));

        $rootScope.usuarioLogueado = true;
        $rootScope.nombreUsuarioLogueado = $cookies.get('username');
        $rootScope.idUsuarioLogueado = parseInt($cookies.get('idUsuario'));
    } 
    else{
        console.log('ANGULAR cookies','no existe cookie');
    }

    //al cambiar de rutas
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
        var requireLogin = toState.data.requireLogin;
        console.log('requireLogin',requireLogin);
        console.log('cookies',$cookies.get('username'));

        if (requireLogin && typeof($cookies.get('username')) == "undefined") {
            event.preventDefault();
            $state.go('login');
            console.log('SE NECESITA LOGIN')
        }

        console.log('RUTA OK')
    });
});
