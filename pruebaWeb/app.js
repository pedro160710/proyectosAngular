var app= angular.module("appBusquedaLibro", []);
app.controller('appController',function($scope){
$scope.libros=[{
		id:1,
		titulo:'Harry Potter',
		autor:'JK Rowling',
		stock:45,
		email:'row@asd.com'
		},
		{
		id:2,
		titulo:'La odisea ',
		autor:'Homero',
		stock:45,
		email:'row@asd.com'
		},
		{
		id:3,
		titulo:'Cien años de soledad ',
		autor:'no se',
		stock:17,
		email:'asdfa@asd.com'
		},
		{
		id:4,
		titulo:'El principito',
		autor:'unknow',
		stock:78,
		email:'kjhkj@asd.com'
		},
		{
		id:5,
		titulo:'La Iliadad',
		autor:'Homero',
		stock:79,
		email:'rlkjw@asd.com'
		},
		{
		id:6,
		titulo:'El alquimista',
		autor:'Paulo cohelo',
		stock:40,
		email:'paul@asd.com'
		},
		{
		id:7,
		titulo:'El horla',
		autor:'guy de maupassant',
		stock:5,
		email:'guy@asd.com'
		},
		{
		id:8,
		titulo:'El libro de la selva',
		autor:'guy de maupassant',
		stock:35,
		email:'guy@asd.com'
		},
		{
		id:9,
		titulo:'Brida',
		autor:'Paulo',
		stock:1,
		email:'aaaaa@asd.com'
		},
		{
		id:10,
		titulo:'Brida',
		autor:'Paulo',
		stock:1,
		email:'aaaaa@asd.com'
		}

		];
	$scope.librosEncontrados=[];
	$scope.contador=0;
	$scope.buscarLibro=function(nombreLibro){
	
		for(var i=0;i<$scope.libros.length; i++){
			console.log('entra for');
			// if($scope.libros[i].titulo===nombreLibro){
			if($scope.libros[i].titulo.indexOf(nombreLibro)>(-1)){
				console.log('encontrado '+ $scope.libros[i].titulo);
				$scope.librosEncontrados[$scope.contador]=$scope.libros[i];	
				$scope.contador=$scope.contador+1;	
			}
		}
	};

	$scope.editarLibro= function(idLibro){
		
	};
	// app.filter('filtroSaluda', function(){
	// 	return function (valorEntrada) {
	// 		var txt= 'hola'+valorEntrada;
	// 		return txt;
	// 	};
	// });
/*
Filtros
currency
date
filter obiene un sub conunto de items de un arreglo
json: covierte de un obj javascript a string json
limitTo: restringe un arreglo o adena a un numero dde elementos o caracteres
lowercase
uppercase
number convierte string con formato a number
orderBy
*/
	

});