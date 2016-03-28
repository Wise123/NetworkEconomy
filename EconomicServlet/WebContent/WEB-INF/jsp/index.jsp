<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		
		<script type="text/javascript" src="resources/lib/jquery-2.2.1.js"></script>
		
		<link rel="stylesheet" href="resources/lib/bootstrap-3.3.6-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="resources/lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
		
		<script type="text/javascript" src="resources/lib/angular-1.5.0/angular.min.js"></script>
		<script type="text/javascript" src="resources/lib/angular-1.5.0/angular-sanitize.js"></script>
		<script type="text/javascript" src="resources/lib/angular-ui-router.js"></script>
		
		<script type="text/javascript" src="resources/app/app.js"></script>
	</head>
	<body ng-app="app" ng-controller="mainController">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="">RisShop</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="" ui-sref="home"><i class="glyphicon glyphicon-home"></i></a></li>
						<li><a href="" ui-sref="catalog({category:'jeans'})">Джинсы</a></li>
						<li><a href="" ui-sref="catalog({category:'tshirts'})">Футболки</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class=""><a href="" ui-sref="cart"><i class="glyphicon glyphicon-shopping-cart"></i> {{(getCart()).length}}</a></li>
						<li><a><i class="glyphicon glyphicon-user"></i> {{(getClient()).surName + ' ' +(getClient()).name + ' ' + (getClient()).lastName}}</a></li>
						<li onclick="logout()"><a href=""><i class="glyphicon glyphicon-off" style="color:red"></i></a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav>
		
		<div class="container" ui-view style="margin-top:50px;">
			
		</div><!-- /.container -->
	</body>
	
	<script type="text/javascript">
		var logout = function(){
			document.location.href = "login";
			
		}
	</script>
	
	
	<script type="text/javascript" src="resources/app/home/homeController.js"></script>
	<script type="text/javascript" src="resources/app/catalog/catalogController.js"></script>
	<script type="text/javascript" src="resources/app/cart/cartController.js"></script>
</html>