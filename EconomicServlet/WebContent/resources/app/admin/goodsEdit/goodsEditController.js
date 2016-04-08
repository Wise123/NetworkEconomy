(function(){
	
	
	
	angular.module('app').controller('goodsEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		$scope.goods = [];
		
		$scope.providers = [];
		
		$scope.addGood = function(){
			$scope.goods.push(angular.copy({idGood:0,idProvider:"1",name:"",price:0,description:"",category:"",countOnStock:0,imagePath:""}))
			
		}
		$scope.removeGood = function(arg){
			for(i in $scope.goods){
				if ($scope.goods[i].idGood == arg.idGood){
					return $scope.goods.splice(i, 1);
				}
			}
		};
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllProviders',
		}).then(function successCallback(response) {
			$scope.providers = response.data;
		}, function errorCallback(response) {
			console.log("Ошибка при получении категорий");
		});
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllGoods',
		}).then(function successCallback(response) {
			$scope.goods = response.data;
		}, function errorCallback(response) {
			console.log("Ошибка при получении товаров");
		});
		
	}]);	
}())