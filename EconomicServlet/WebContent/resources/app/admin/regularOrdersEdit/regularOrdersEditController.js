(function(){
	
	
	
	angular.module('app').controller('regularOrdersEditController', ['$scope','$sce', '$state','$http', function($scope, $sce, $state, $http){
		if (angular.equals(client,{})){
			$state.go('login');
		}
		
		$scope.regularOrders = [];

		
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllRegularOrdersInfo',
		}).then(function successCallback(response) {
			$scope.regularOrders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении подписок");
		});
		
		
		$scope.removeRegularOrder = function(order){
			$http({
				method: 'GET',
				url: urlPath+'/deleteRegularOrder',
				params:{idRegOrder:order.idOrder}
			}).then(function successCallback(response) {
				
				$http({
					method: 'GET',
					url: urlPath+'/getAllRegularOrdersInfo',
				}).then(function successCallback(response) {
					$scope.regularOrders = angular.copy(response.data);
				}, function errorCallback(response) {
					console.log("Ошибка при получении подписок");
				});
				
				
			}, function errorCallback(response) {
				console.log("Ошибка при удалении подписок");
			});
		}
		
		
		
		
		
	}]);	
}())