(function(){
	angular.module('app').controller('homeController', ['$scope','$sce', '$state', function($scope, $sce, $state){
		if (angular.equals(client, {})){
			$state.go("login");
		}
		console.log("Welcome");
	
	}]);	
}())