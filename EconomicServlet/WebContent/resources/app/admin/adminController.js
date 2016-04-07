(function(){
	
	
	
	angular.module('app').controller('adminController', ['$scope','$sce', '$state', function($scope, $sce, $state){
		$scope.currentTab = 0;
		$state.go("admin.goodsEdit");
		$scope.getClassForTab = function(tabNum){
			if (tabNum == $scope.currentTab){
				return "active";
			}
			else{
				return "";
			}
		};
		
		$scope.openTab = function(tabNum){
			$scope.currentTab = tabNum;
			switch(tabNum){
				case 0:{
					$state.go("admin.goodsEdit");
					break;
				};
				case 1:{
					$state.go("admin.ordersEdit");
					break;
				};
				case 2:{
					$state.go("admin.regularOrdersEdit");
					break;
				};
				default:{
					alert("Ошибка!");
					$state.go("admin");
				};
			};
		};
		
	}]);	
}())