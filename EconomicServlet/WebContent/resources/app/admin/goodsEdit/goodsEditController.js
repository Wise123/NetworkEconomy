(function(){
	
	
	
	angular.module('app').controller('goodsEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		$scope.goods = [];
		
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