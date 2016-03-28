var app = angular.module('app',["ui.router",'ngSanitize']);
 


var cart = [];
var client = {
		idClient:1,
		name: "Иван",
		lastName: "Иванович",
		surName: "Иванов",
		city: "Москва",
		country: "Россия",
		postIndex: 123456,
		password: 123456,
};

app.controller('mainController', ['$scope', function($scope) {
	$scope.getCart = function(){
		return cart;
	}
	
	$scope.getClient = function(){
		return client;
	}
	
}]);

app.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("");
	
	$stateProvider.state("home", {
		url: "/home",
		templateUrl:"resources/app/home/homeView.html",
		controller:"homeController"
	})
	.state("catalog", {
		url: "/catalog/:category",
		templateUrl:"resources/app/catalog/catalogView.html",
		controller:"catalogController"
	}).state("cart", {
		url: "/cart",
		templateUrl:"resources/app/cart/cartView.html",
		controller:"cartController"
	});
});