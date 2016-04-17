(function(){
	
	
	
	angular.module('app').controller('providersEditController', ['$scope','$sce', '$state', '$http', function($scope, $sce, $state, $http){
		
		
		$scope.providers = [];
		
		$scope.originProviders = [];
		
		
		
		
		
		$http({
			method: 'GET',
			url: urlPath+'/getAllProviders',
		}).then(function successCallback(response) {
			$scope.providers = angular.copy(response.data);
			$scope.originProviders = angular.copy(response.data);
		}, function errorCallback(response) {
			console.log("Ошибка при получении поставщиков");
		});
		
		
		$scope.saveProviders = function(){
			for (i in $scope.providers){
				for (j in $scope.originProviders){
					console.log($scope.providers[i].idProvider);
					console.log($scope.originProviders[j].idProvider);
					if ($scope.providers[i].idProvider === $scope.originProviders[j].idProvider){
						if (!angular.equals($scope.providers[i],$scope.originProviders[j])){
							console.log($scope.providers[i]);
							$http({
								method: 'POST',
								url: urlPath+'/updateProvider',
								params:{jsonProvider:angular.toJson($scope.providers[i])}
							}).then(function successCallback(response) {
							}, function errorCallback(response) {
								console.log("Ошибка при обновлении товаров");
							});
						}
					}
				}
			}
			
			for (i in $scope.providers){
				var isCreated = true;
				for (j in $scope.originProviders){
					if($scope.providers[i].idProvider==$scope.originProviders[j].idProvider){
						isCreated=false;
					}
				}
				if (isCreated){
					console.log($scope.providers[i].idProvider);
					$http({
						method: 'POST',
						url: urlPath+'/createProvider',
						params:{jsonProvider:angular.toJson($scope.providers[i])}
					}).then(function successCallback(response) {
					}, function errorCallback(response) {
						console.log("Ошибка при добавлении товаров");
					});
				}
			}
			
			for (j in $scope.originProviders){
				var isRemoved = true;
				for (i in $scope.providers){
					if($scope.providers[i].idProvider==$scope.originProviders[j].idProvider){
						isRemoved=false;
					}
				}
				if (isRemoved){
					console.log($scope.originProviders[j].idProvider);
					$http({
						method: 'POST',
						url: urlPath+'/deleteProvider',
						params:{idProvider:$scope.originProviders[j].idProvider}
					}).then(function successCallback(response) {
					}, function errorCallback(response) {
						console.log("Ошибка при удалении товаров");
					});
				}
			}
		}
		
		
		$scope.addProvider = function(){
			var maxId = 0;
			for( var i in $scope.providers){
				if($scope.providers[i].idProviders > maxId){
					maxId = $scope.providers[i].idProviders;
				}
			}
			$scope.providers.push(angular.copy({idProvider:parseInt($scope.providers[i].idProvider)+1+'',"address":"","title":"","description":""}));
		}
		$scope.removeProvider = function(arg){
			for(i in $scope.providers){
				if ($scope.providers[i].idProvider == arg.idProvider){
					return $scope.providers.splice(i, 1);
				}
			}
		};
		
	}]);	
}())