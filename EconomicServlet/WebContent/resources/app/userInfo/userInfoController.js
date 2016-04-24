(function(){
	
	
	
	angular.module('app').controller('userInfoController', ['$scope','$sce', '$state',"$http", '$stateParams', function($scope, $sce, $state, $http, $stateParams){
		
		if (angular.equals(client,{})){
			$state.go('login');
		}
		
		$scope.orders = [];
		
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getOrdersByUser',
			params:{
				userId:client.idClient
			}
		}).then(function successCallback(response) {
			$scope.orders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении заказов");
		});
		
		
		$scope.removeOrder = function(order){
			$http({
				method: 'GET',
				url: urlPath+'/deleteOrder',
				params:{idOrder:order.idOrder}
			}).then(function successCallback(response) {
				
				$http({
					method: 'GET',
					url: urlPath+'/getOrdersByUser',
					params:{
						userId:client.idClient
					}
				}).then(function successCallback(response) {
					$scope.orders = angular.copy(response.data);
				}, function errorCallback(response) {
					console.log("Ошибка при получении заказов");
				});
				
				
			}, function errorCallback(response) {
				console.log("Ошибка при удалении заказа");
			});
		}
		
		
		$scope.fio = function(){
			return client.name +' '+ client.surname + ' ' + client.lastName;
		}
		
		$scope.getClient = function(){
			return client;
		}
		
		
		$scope.regularOrders = [];

		
		
		$http({
			method: 'GET',
			url: urlPath+'/getRegularOrdersByUser',
			params:{
				userId:client.idClient
			}
		}).then(function successCallback(response) {
			$scope.regularOrders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении подписок");
		});
		
		
		$scope.removeRegularOrder = function(order){
			$http({
				method: 'GET',
				url: urlPath+'/deleteRegularOrder',
				params:{idRegOrder:order.idRegord}
			}).then(function successCallback(response) {
				
				$http({
					method: 'GET',
					url: urlPath+'/getRegularOrdersByUser',
					params:{
						userId:client.idClient
					}
				}).then(function successCallback(response) {
					$scope.regularOrders = angular.copy(response.data);
				}, function errorCallback(response) {
					console.log("Ошибка при получении подписок");
				});
				
				
			}, function errorCallback(response) {
				console.log("Ошибка при удалении подписок");
			});
		}
		
		
		$scope.changePassword = function(){
			if (client.password == window.prompt("Введите старый пароль")){
				$http({
					method: 'GET',
					url: urlPath+'/changePassword',
					params:{
						id:client.idClient,
						newPassword: window.prompt("Введите новый пароль пароль")
					}
				}).then(function successCallback(response) {
					$scope.regularOrders = angular.copy(response.data);
				}, function errorCallback(response) {
					console.log("Ошибка при получении подписок");
				});
			}
			else{
				window.alert("Неверный пароль")
			}
		}
		
		
	}]);
}())