(function(){
	
	
	
	angular.module('app').controller('goodsEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		$scope.goods = [];
		$scope.originGoods = []
		
		$scope.providers = [];
		
		$scope.addGood = function(){
			var maxId = 0;
			for( var i in $scope.goods){
				console.log(maxId);
				console.log($scope.goods[i].idGood);
				if($scope.goods[i].idGood > maxId){
					maxId = $scope.goods[i].idGood;
				}
			}
			$scope.goods.push(angular.copy({idGood:parseInt($scope.goods[i].idGood)+1+'',idProvider:"1",name:"",price:0,description:"",category:"",countOnStock:0,imagePath:""}));
		}
		$scope.removeGood = function(arg){
			for(i in $scope.goods){
				if ($scope.goods[i].idGood == arg.idGood){
					return $scope.goods.splice(i, 1);
				}
			}
		};
		
		
		
		$scope.saveGoods = function(){
			for (i in $scope.goods){
				for (j in $scope.originGoods){
					if ($scope.goods[i].idGood === $scope.originGoods[j].idGood){
						if (!angular.equals($scope.goods[i],$scope.originGoods[j])){
							console.log($scope.goods[i]);
						}
					}
				}
			}
			
		}
		
		
		
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
			$scope.goods = angular.copy(response.data);
			$scope.originGoods = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении товаров");
		});
		
	}]);	
}())