(function(){
	
	
	
	angular.module('app').controller('orderEditController', ['$scope','$sce', '$state',"$http", "$stateParams", function($scope, $sce, $state, $http, $stateParams){
		if (angular.equals(client,{})){
			$state.go('login');
		}
		$scope.orders = [];
		$scope.goods = [];
		
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
			$scope.goods = angular.copy(response.data);
			console.log($scope.goods)
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказа");
		});
		
		
	}]);	
}())