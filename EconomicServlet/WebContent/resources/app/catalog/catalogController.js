(function(){
	angular.module('app').controller('catalogController', ['$scope','$sce', '$stateParams', '$http',function($scope, $sce, $stateParams, $http){
		console.log("Catalog: " + $stateParams.category);
		
		$scope.catalog = [];
		$scope.category= $stateParams.category;
		
		$http({
			method: 'GET',
			url: urlPath+'/getGoodsByCategory',
			params:{category:$stateParams.category}
		}).then(function successCallback(response) {
			$scope.catalog = response.data;
		}, function errorCallback(response) {
			console.log("Ошибка при получении каталога товаров");
		});
		
		
		$scope.addToCart = function(arg){
			cart.push(arg);
		};
		
		$scope.removeFromCart = function(arg){
			cart.pop(cart.indexOf(arg));
		};
		
		
		$scope.isInCart = function(arg){
			return cart.indexOf(arg) != -1;
		}
		
		
	}]);
}())