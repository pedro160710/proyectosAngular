var app= angular.module('appFiltros',[]);
app.controller('filCtrl', function($scope){
	$scope.friends = [{nombre:'John', telefono:'555-1276'},
	{nombre:'Mary', telefono:'800-BIG-MARY'},
	{nombre:'Mike', telefono:'555-4321'},
	{nombre:'Adam', telefono:'555-5678'},
	{nombre:'Julie', telefono:'555-8765'},
	{nombre:'Juliette', telefono:'555-5678'}];

	$scope.moneda=465;
	$scope.numero=123456;
	$scope.fecha = "2016-12-10";
	// $scope.fecha = "2016-12-10";
	$scope.string="Esto Es un String";
	$scope.numeroPrimo=7;
	$scope.numeroPar =6;
	$scope.numeroImpar= 9;

});

	// filtro personalizado
	app.filter('filtroSaluda', function(){
		return function(x){
			var txtHola = "hola"+x;
			return txtHola;
		};

	});

app.filter('filtroPrimo',function(){
	return function(texto){
		var numPrimo;
		if(texto%2===1 && texto%3===1){
 			return true;
		}else {
			return false;
		}

	};
});