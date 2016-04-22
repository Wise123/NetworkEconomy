(function(){
	
	
	
	angular.module('app').controller('cartController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		if (angular.equals(client, {})){
			$state.go("login");
		}
		console.log("Cart");
		
		
		$scope.getCart = function(){
			console.log(cart);
			return cart;
		}
		
		
		$scope.cartSum = function(){
			var ans = 0;
			for (i in cart){
				ans = ans + cart[i].price * cart[i].number;
			}
			return ans;
		}
		
		$scope.removeFromCart = function(arg){
			for(i in cart){
				if (cart[i].idGood == arg.idGood){
					return cart.splice(i, 1);
				}
			}
		};
		
		
		$scope.createOrder = function(){
			if (cart.length == 0 || !window.confirm("Оформить заказ?")){
				return;
			}
			$http({
				method: 'GET',
				url: urlPath+'/getAllOrdersInfo',
			}).then(function successCallback(response) {
				$scope.orders = angular.copy(response.data);
				
				console.log($scope.orders)
				var maxId = 0;
				for( var i in $scope.orders){
					if($scope.orders[i].idOrder > maxId){
						maxId = $scope.orders[i].idOrder;
					}
				}
				
				
				$http({
					method: 'POST',
					url: urlPath+'/createOrder',
					params:{idClient: client.idClient,idOrder:maxId+1, goodsJson:(angular.toJson(cart))}
				}).then(function successCallback(response) {
				}, function errorCallback(response) {
					console.log("Ошибка при оформлении заказа");
				});
				alert('Заказ оформлен. \n \n Спасибо за покупку!')
				
			}, function errorCallback(response) {
				console.log("Ошибка при получении заказов");
			});
		}
			
			
		$scope.createRegularOrder = function(){
			if (cart.length == 0 || !window.confirm("Оформить подписку?")){
				return;
			}
			$http({
				method: 'GET',
				url: urlPath+'/getAllRegularOrdersInfo',
			}).then(function successCallback(response) {
				$scope.regularOrders = angular.copy(response.data);
				
				console.log($scope.regularOrders)
				var maxId = 0;
				for( var i in $scope.regularOrders){
					if($scope.regularOrders[i].idRegord > maxId){
						maxId = $scope.regularOrders[i].idRegord;
					}
				}
				
				
				$http({
					method: 'POST',
					url: urlPath+'/createRegularOrder',
					params:{idClient: client.idClient,countOfMonth:window.prompt("введите длительность попытки в месяцах","1"),idRegOrder:maxId+1, goodsJson:(angular.toJson(cart))}
				}).then(function successCallback(response) {
				}, function errorCallback(response) {
					console.log("Ошибка при оформлении заказа");
				});
				alert('Подписка оформлена . \n \n Спасибо за покупку!')
				
			}, function errorCallback(response) {
				console.log("Ошибка при получении подписок");
			});	
		}
		
		
		
	}]);	
}())