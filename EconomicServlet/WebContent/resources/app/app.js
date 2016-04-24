var app = angular.module('app',["ui.router",'ngSanitize','angularFileUpload']);
 


var cart = [];
var client = {};


app.directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result;
                    });
                }
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);


app.controller('mainController', ['$scope', '$http', function($scope, $http) {
	$scope.getCart = function(){
		return cart;
	}
	
	$scope.getClient = function(){
		return client;
	}
	
	$scope.showAdmin = function(){
		return client.admin;
	}
	
	$scope.$on("categoryChanged", function(event, data){
		console.log('categoryChanged');
		$http({
			method: 'GET',
			url: urlPath+'/getAllCategories',
		}).then(function successCallback(response) {
			$scope.categories = response.data;
		}, function errorCallback(response) {
			console.log("Ошибка при получении категорий");
		});
	});
	
	$scope.categories = [];
	
	$http({
		method: 'GET',
		url: urlPath+'/getAllCategories',
	}).then(function successCallback(response) {
		$scope.categories = response.data;
	}, function errorCallback(response) {
		console.log("Ошибка при получении категорий");
	});
	
}]);

app.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("login");
	
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
	}).state("login", {
		url: "/login",
		templateUrl:"resources/app/login/loginView.html",
		controller:"loginController"
	}).state("admin", {
		url: "/admin",
		templateUrl:"resources/app/admin/adminView.html",
		controller:"adminController"
	}).state("admin.goodsEdit", {
		url: "/goodsEdit",
		templateUrl:"resources/app/admin/goodsEdit/goodsEditView.html",
		controller:"goodsEditController"
	}).state("admin.ordersEdit", {
		url: "/ordersEdit",
		templateUrl:"resources/app/admin/ordersEdit/ordersEditView.html",
		controller:"ordersEditController"
	}).state("admin.regularOrdersEdit", {
		url: "/regularOrdersEdit",
		templateUrl:"resources/app/admin/regularOrdersEdit/regularOrdersEditView.html",
		controller:"regularOrdersEditController"
	}).state("admin.providersEdit", {
		url: "/providersEdit",
		templateUrl:"resources/app/admin/providersEdit/providersEditView.html",
		controller:"providersEditController"
	}).state("orderEdit", {
		url: "/orderEdit/:id",
		templateUrl:"resources/app/admin/ordersEdit/orderEdit/orderEditView.html",
		controller:"orderEditController"
	})
	
	
	
	;
});