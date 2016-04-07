(function(){
	angular.module('app').controller('catalogController', ['$scope','$sce', '$stateParams', '$http', '$state',function($scope, $sce, $stateParams, $http, $state){
		if (angular.equals(client, {})){
			$state.go("login");
		}
		console.log("Catalog: " + $stateParams.category);
		
		$scope.catalog = [];
		$scope.category= $stateParams.category;
		
		$http({
			method: 'GET',
			url: urlPath+'/getGoodsByCategory',
			params:{category:$stateParams.category}
		}).then(function successCallback(response) {
			$scope.catalog = response.data;
			for(i in $scope.catalog){
				$scope.catalog[i].number = 1;
			}
		}, function errorCallback(response) {
			console.log("Ошибка при получении каталога товаров");
		});
		
		
		$scope.addToCart = function(arg){
			if(arg.countOnStock >= arg.number){
				cart.push(arg);
			}
			else{
				alert("В наличии есть не более "+ arg.countOnStock+" штук.")
			}
		};
		
		$scope.removeFromCart = function(arg){
			for(i in cart){
				if (cart[i].idGood == arg.idGood){
					return cart.splice(i, 1);
				}
			}
		};
		
		
		$scope.isInCart = function(arg){
			ans = false
			for(i in cart){
				if (cart[i].idGood == arg.idGood){
					ans = true;
				}
			}
			return ans;
		}
		
		
	}]);
}())