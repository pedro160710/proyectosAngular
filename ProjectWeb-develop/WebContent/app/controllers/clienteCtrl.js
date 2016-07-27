aplicacion.controller('clientesCtrl',['$scope','$http','$state','toastr','$rootScope','$uibModal','ClienteSrv',
    function($scope,$http,$state, toastr,$rootScope, $uibModal,ClienteSrv){

    console.log('CLIENTE');

    $scope.listaClientes = [];
    $scope.nombreClienteBusqueda = '';

    // Traer a todos los clientes desde el servidor 
    obtenerTodosLosClientes(); 
    
    // no usar filtros, usar el api de busquedas
    $scope.buscarClientePorNombre = function(){

        if($scope.nombreClienteBusqueda == ''){
            obtenerTodosLosClientes();
        }
        else{
            buscarCliente();
        }
    };

    function obtenerTodosLosClientes(){

    	ClienteSrv
            .obtenerClientes()
            .then(
                function (respuesta){

    			    // Si  se obtuvieron clientes
                    if(respuesta.data && respuesta.status == 200){
                	    $scope.listaClientes = respuesta.data;
                    }
                    else{
                	    toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }
                },
                function (error){
                    if(error.status==500){
                	   toastr.error('Error interno en el servidor','Error');
                    }
                    if(error.status==404){
                	   toastr.warning('No existen clientes','Advertencia');
                    }
                }
            );
    }

    function buscarCliente(){

        ClienteSrv
            .buscarClientesPorNombre($scope.nombreClienteBusqueda)
            .then(
                function obtenerClientes(respuesta){

                    // Si  se encontro algun cliente
                    if(respuesta.data){
                        $scope.listaClientes = respuesta.data;
                    }
                    else{
                        toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }
                },
                function errorObtenerClientes(error){
                    
                    if(error.status==500){
                        toastr.error('Error interno en el servidor','Error');
                    }
                    if(error.status==404){
                        toastr.warning('No se ha encontrado al cliente','Advertencia');
                    }
                }
            );
    }

    // MODAL ELIMINAR CLIENTE
    $scope.abrirModalEliminarCliente = function (cliente) {

        // Abre un modal con la siguiente configuracion
        var instanciaModalEliminarCliente = $uibModal.open({
            animation: true,
            templateUrl: 'modalEliminarCliente.html',// esto esta en clientes.html
            controller: 'modalEliminarClienteCtrl',
            size: 'sm',
            // Valores a inyectarse en modalEliminarClienteCtrl
            resolve: {
                idCliente: function () {
                    return cliente.idCliente;
                },
                nombreCliente: function() {
                    return cliente.cliNombre;
                }
            }
        });

        // Recibe el resultado del modal luego de cerrarse dicho modal (luego de dar click en eliminar
        // o de cancelar el modal)
        instanciaModalEliminarCliente
            .result
            .then(function (respuesta) {
                console.log('respuesta modal',respuesta);
                if(!respuesta.error){
                    
                    // Buscar en la listas de cliente y eliminar en la vista
                    for (var i = 0; i < $scope.listaClientes.length; i++) {
                        if($scope.listaClientes[i].idCliente == respuesta.idClienteEliminado){
                            $scope.listaClientes.splice(i,1);
                        }
                    }
                }
            }, 
            function modalCancelado() {
                console.log('Modal cancelado');
            }
        );
    };

    // OBTENER REPORTE
    $scope.obtenerReporte = function(){
        ClienteSrv
            .obtenerReporteClientes()
            .then(
                function(respuesta){

                    // Si se crea el cliente
                    if(respuesta.data && respuesta.status == 200){
                        
                        var reportePDF = new Blob([respuesta.data], {type: 'application/pdf'});
                        var reporteURL = URL.createObjectURL(reportePDF);
                        window.open(reporteURL);
                        console.log(reporteURL);

                        toastr.success('Reporte generado', 'Clientes');
                    }
                    else{
                        toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }

                },
                function (error){
                    if(error.status==500){
                        toastr.error('Error interno en el servidor','Error');
                    }
                    
                }
            );

    }

}]);

aplicacion.controller('crearClienteCtrl',['$scope','$http','$state','toastr','$rootScope','ClienteSrv',
    function($scope,$http,$state, toastr,$rootScope,ClienteSrv){

    console.log('CREAR CLIENTE');

    $scope.nuevoCliente = {
    	cedula: null,
    	correo: null,
    	direccion: null,
        telefono: null,
    	movil: null,
    	nombre: null,
    	razonSocial: null
    };

    $scope.crearCliente = function(){

        ClienteSrv
            .crearCliente($scope.nuevoCliente)
            .then(
                function(respuesta){

        			// Si se crea el cliente
                    if(respuesta.data && respuesta.status == 201){
                        
                        $scope.nuevoCliente = {
        			    	cedula: null,
        			    	correo: null,
        			    	direccion: null,
                            telefono: null,
        			    	movil: null,
        			    	nombre: null,
        			    	razonSocial: null
        			    };
                        
                        $state.go("clientes");
                        toastr.success('Cliente creado correctamente', 'Clientes');
                    }
                    else{
                    	toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }

                },
                function (error){
                    if(error.status==500){
                    	toastr.error('Error interno en el servidor','Error');
                    }
                    
                }
            );
    };
    
}]);

aplicacion.controller('editarClienteCtrl',['$scope','$http','$state','toastr','$rootScope','$stateParams','ClienteSrv',
    function($scope,$http,$state, toastr,$rootScope,$stateParams,ClienteSrv){

    // id que viene desde la url con params
    // http://127.0.0.1:9000/#/editarCliente/1
    $scope.idCliente = $stateParams.idCliente;

    $scope.datosCliente = {
        cedula: null,
        correo: null,
        direccion: null,
        telefono: null,
        movil: null,
        nombre: null,
        razonSocial: null
    };

    console.log('EDITAR CLIENTE');

    //1. Con el id traer nuevamente el cliente desde la api rest
    ClienteSrv
        .obtenerClientePorId($scope.idCliente)
        .then(
            function obtenerClienteApiRest(respuesta){

                // Si se recupera el cliente
                if(respuesta.data && respuesta.status == 200){
                    
                    // Establecer los datos y mostrarlos en el formulario
                    $scope.datosCliente.cedula = parseInt(respuesta.data.cliCedula);
                    $scope.datosCliente.correo = respuesta.data.cliCorreo;
                    $scope.datosCliente.direccion = respuesta.data.cliDireccion;
                    $scope.datosCliente.telefono = respuesta.data.cliTelefono;
                    $scope.datosCliente.movil = respuesta.data.cliMovil;
                    $scope.datosCliente.nombre = respuesta.data.cliNombre;
                    $scope.datosCliente.razonSocial = respuesta.data.cliRazonSocial;
                    console.log($scope.datosCliente);
                }
                else{
                    toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                }
            },
            function (error){
                if(error.status==500){
                    toastr.error('Error interno en el servidor','Error');
                }
                if(error.status==404){
                    toastr.error('No se pudo obtener los datos del cliente','Error');
                }
                
            }
        );

    $scope.editarCliente = function(){

        ClienteSrv
            .editarCliente($scope.idCliente,$scope.datosCliente)
            .then(
                function (respuesta){

                    // Si se actualiza el cliente
                    if(respuesta.data && respuesta.status == 200){
                        
                        $scope.datosCliente = {
                            cedula: null,
                            correo: null,
                            direccion: null,
                            telefono: null,
                            movil: null,
                            nombre: null,
                            razonSocial: null

                        };
                        
                        $state.go("clientes");
                        toastr.success('Cliente editado correctamente', 'Clientes');
                    }
                    else{
                        toastr.error('Error interno en el servidor','Error');//respuesta.statusText
                    }
                },
                function (error){
                    if(error.status==500){
                        toastr.error('Error interno en el servidor','Error');
                    }           
                }
            );  
    };

}]);

aplicacion.controller('modalEliminarClienteCtrl', ['$scope','$http','toastr','$uibModalInstance','idCliente','nombreCliente','ClienteSrv',
 function($scope,$http,toastr,$uibModalInstance,idCliente,nombreCliente,ClienteSrv){

    //VARIABLES SCOPE
    $scope.idCliente = idCliente;
    $scope.nombreCliente = nombreCliente;

    //******************************** METODOS **********************************//

    // Boton eliminar cliente
    $scope.confirmarEliminarCliente = function () {
        eliminarCliente();
    };

    // Boton cancelar eliminacion de cliente
    $scope.cancelarEliminarCliente = function () {
        $uibModalInstance.dismiss('cancel');
    };

    function eliminarCliente(){

        ClienteSrv
            .eliminarCliente($scope.idCliente)
            .then(
                function (respuesta){

                    if(!respuesta.error){
                        toastr.success('Cliente '+$scope.nombreCliente+' eliminado', 'Exito');
                    
                        // Devolver a ClientesController el siguiente objeto
                        $uibModalInstance.close({
                            error: false,
                            idClienteEliminado: $scope.idCliente
                        });
                    }
                    else{
                        toastr.error('Error interno en el servidor','Error');
                    
                        // Devolver a ClientesController el siguiente objeto
                        $uibModalInstance.close({
                            error: true
                        });
                    }
                },
                function (error){
                    if(error.status==500){
                        toastr.error('Error interno en el servidor','Error');
                    }           
                }
            ); 
    }

}]);

