(function(){
	
	
	
	angular.module('app').controller('orderEditController', ['$scope','$sce', '$state',"$http", "$stateParams", function($scope, $sce, $state, $http, $stateParams){
		
		$scope.orders = [];
		$scope.order = {};
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllOrdersInfo',
		}).then(function successCallback(response) {
			$scope.orders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказов");
		});
		
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getOrder',
			params:{
				orderId:$stateParams.id
			}
		}).then(function successCallback(response) {
			$scope.order = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказа");
		});
		
		
	}]);	
}())