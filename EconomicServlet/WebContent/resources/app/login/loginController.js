(function(){
	angular.module('app').controller('loginController', ['$scope','$sce', '$state', '$http',function($scope, $sce, $state, $http){
		
		$(".nav").hide();
		
		
		$scope.login = function(){
			$http({
				method: 'GET',
				url: urlPath+'/login',
				params:{'name':$scope.name,'password':$scope.password}
			}).then(function successCallback(response) {
				
				if(response.data.name !=undefined){
					client = response.data;
					$(".nav").show();
					$state.go('home');
				}
				else{
					alert("Неверный пароль");
				}
				
			}, function errorCallback(response) {
				alert("Ошибка при отправке пароля");
			});
		}
		
	}]);
}())