(function(){
	
	
	
	angular.module('app').controller('cartController', ['$scope','$sce', '$state', function($scope, $sce, $state){
		if (angular.equals(client, {})){
			$state.go("login");
		}
		console.log("Cart");
		
		
		$scope.getCart = function(){
			console.log(cart);
			return cart;
		}
		
		
		$scope.cartSum = function(){
			var ans = 0;
			for (i in cart){
				ans = ans + cart[i].price * cart[i].number;
			}
			return ans;
		}
		
		$scope.removeFromCart = function(arg){
			for(i in cart){
				if (cart[i].idGood == arg.idGood){
					return cart.splice(i, 1);
				}
			}
		};
		
	}]);	
}())