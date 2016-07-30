var app = angular.module('appNgRoute',['ngRoute']);
app.config(function($routeProvider){
// set up routes
$routeProvider
	.when('/',{	
		templateUrl:'partials/home.html'

	});
});