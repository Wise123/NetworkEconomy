(function(){
	
	
	
	angular.module('app').controller('providersEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		
		$scope.providers = [];
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllProviders',
		}).then(function successCallback(response) {
			$scope.providers = response.data;
		}, function errorCallback(response) {
			console.log("Ошибка при получении поставщиков");
		});
		
		
	}]);	
}())