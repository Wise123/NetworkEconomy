(function(){
	
	
	
	angular.module('app').controller('regularOrderEditController', ['$scope','$sce', '$state',"$http", "$stateParams", function($scope, $sce, $state, $http, $stateParams){
		if (angular.equals(client,{})){
			$state.go('login');
		}
		$scope.regularOrders = [];
		$scope.goods = [];
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllRegularOrdersInfo',
		}).then(function successCallback(response) {
			$scope.reguarOrders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказов");
		});
		
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getRegularOrder',
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