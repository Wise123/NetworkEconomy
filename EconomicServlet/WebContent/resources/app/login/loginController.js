(function(){
	angular.module('app').controller('loginController', ['$scope','$sce', '$state', '$http',function($scope, $sce, $state, $http){
		
		$(".nav").hide();
		
		
		$scope.login = function(){
			$http({
				method: 'GET',
				url: urlPath+'/login',
				params:{login:$scope.login, password:$scope.password}
			}).then(function successCallback(response) {
				$(".nav").show();
				$state.go('home');
			}, function errorCallback(response) {
				alert("Ошибка при отправке пароля");
				$(".nav").show();
				$state.go('home');//убрать когда появится обработчик
			});
		}
		
	}]);
}())