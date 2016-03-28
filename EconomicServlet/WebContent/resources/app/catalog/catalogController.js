(function(){
	angular.module('app').controller('catalogController', ['$scope','$sce', '$stateParams',function($scope, $sce, $stateParams){
		console.log("Catalog: " + $stateParams.category);
	
	}]);	
}())