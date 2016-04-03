(function(){
	angular.module('app').controller('cartController', ['$scope','$sce', function($scope, $sce){
		console.log("Cart");
		
		
		$scope.getCart = function(){
			console.log(cart);
			return cart;
		}
		
		
		$scope.cartSum = function(){
			var ans = 0;
			for (i in cart){
				ans = ans + cart[i].price
			}
			return ans;
		}
		
	}]);	
}())