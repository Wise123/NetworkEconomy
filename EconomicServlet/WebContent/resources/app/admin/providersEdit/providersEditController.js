(function(){
	
	
	
	angular.module('app').controller('providersEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		
		$scope.providers = [];
		
		$scope.originProviders = [];
		
		
		
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllProviders',
		}).then(function successCallback(response) {
			$scope.providers = response.data;
			$scope.originProviders = response.data
		}, function errorCallback(response) {
			console.log("Ошибка при получении поставщиков");
		});
		
		
	}]);	
}())