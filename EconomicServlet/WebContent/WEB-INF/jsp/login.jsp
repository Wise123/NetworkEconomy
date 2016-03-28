<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Вход в аккаунт</title>
		
			<script type="text/javascript" src="resources/lib/jquery-2.2.1.js"></script>
			
			<link rel="stylesheet" href="resources/lib/bootstrap-3.3.6-dist/css/bootstrap.min.css">
			<script type="text/javascript" src="resources/lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
			
			<script type="text/javascript" src="resources/lib/angular-1.5.0/angular.min.js"></script>
	</head>
	<body>
		<div class="form-signin" style="width:34%;margin-left:33%;height:34%; margin-top:23%">
			<h2 class="form-signin-heading">Войдите в аккаунт</h2>
			<input type="email" id="inputEmail" class="form-control" placeholder="Логин" required autofocus>
			<br>
			<input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required>
			<br>
			<button class="btn btn-lg btn-default btn-block" type="submit" onclick="login()">Вход</button>
		</div>
	</body>
	
	<script type="text/javascript">
		var login = function(){
			document.location.href = "index?login=" + $("#inputEmail").val() + "&password="+ $("#inputPassword").val() + "#/home";
			
		}
	</script>
	
	
</html>