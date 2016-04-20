(function(){
	
	
	
	angular.module('app').controller('ordersEditController', ['$scope','$sce', '$state',"$http", '$stateParams', function($scope, $sce, $state, $http, $stateParams){
		
		$scope.orders = [];

		
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllOrdersInfo',
		}).then(function successCallback(response) {
			$scope.orders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказов");
		});
		
		
		$scope.removeOrder = function(order){
			$http({
				method: 'GET',
				url: urlPath+'/deleteOrder',
				params:{id_Order:order.idOrder}
			}).then(function successCallback(response) {
				
				$http({
					method: 'GET',
					url: urlPath+'/getAllOrdersInfo',
				}).then(function successCallback(response) {
					$scope.orders = angular.copy(response.data);
				}, function errorCallback(response) {
					console.log("Ошибка при получении заказов");
				});
				
				
			}, function errorCallback(response) {
				console.log("Ошибка при удалении заказа");
			});
		}
		
		
	}]);
}())