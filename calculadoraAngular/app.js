var app=angular.module("Calculator",[]);
app.controller("CalculatorController",function($scope){
	$scope.displayValue = 0;
	$scope.memory= null;
	$scope.result= 0;
	$scope.operation = null;

	$scope.saveMemory = function(){
		if($scope.memory === null){
			$scope.memory = parseFloat($scope.displayValue);
		}
	};
	$scope.onClicClear= function(){
		$scope.operation= 0;
		$scope.memory = null;
		$scope.displayValue= 0;
		$scope.result=0;
	};
	$scope.onClicSum= function(){
		$scope.saveMemory();
		$scope.operation ='+';
		$scope.displayValue=0;
	};
	$scope.onClicSubstract= function(){
		$scope.saveMemory();
		$scope.operation='-';
		$scope.displayValue=0;
	};
	$scope.onClicMultiply= function(){
		$scope.saveMemory();
		$scope.operation='*';
		$scope.displayValue=0;
	};
	$scope.onClicDivide = function(){
		$scope.saveMemory();
		$scope.operation='/';
		$scope.displayValue=0;
	};
	$scope.onClicCalculate = function(){
		if($scope.operation==='+'){
			$scope.result = parseFloat($scope.memory)+	parseFloat($scope.displayValue);
		}
		else if($scope.operation==='-'){
			$scope.result= parseFloat($scope.memory) - parseFloat($scope.displayValue);
		}
		else if($scope.operation==='*'){
			$scope.result= parseFloat($scope.memory) * parseFloat($scope.displayValue);
		}
		else if($scope.operation==='/'){
			$scope.result= parseFloat($scope.memory) / parseFloat($scope.displayValue);
		}
		$scope.memory = $scope.result;

	};
});