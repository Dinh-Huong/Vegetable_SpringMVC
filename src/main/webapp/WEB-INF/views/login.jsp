<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"
	value="${ pageContext.servletContext.contextPath }" scope="session" />
	
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V8</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/css/util.css">
	<link rel="stylesheet" type="text/css" href="${ contextPath }/user/loginUser/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="post">
					<span class="login100-form-title">
						Sign In
					</span>
					<c:if test="${ !empty error }">
						<div class="alert alert-danger alert-dismissible fade show"
							role="alert">
							<strong>${ error }</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>
					<div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email">
						<input class="input100" type="text"  value="${ !empty email ? email : '' }" name="email" placeholder="Email">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Please enter password">
						<input class="input100" type="password" value="${ !empty password ? password : '' }" name="password" placeholder="Password">
						<span class="focus-input100"></span>
					</div>

					<div class="text-right p-t-13 p-b-23">
						<span class="txt1">
							Forgot
						</span>

						<a href="#" class="txt2">
							Username / Password?
						</a>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Sign in
						</button>
					</div>

					<div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Don’t have an account?
						</span>

						<a href="${ contextPath }/register" class="txt3">
							Sign up now
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/bootstrap/js/popper.js"></script>
	<script src="${ contextPath }/user/loginUser/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/daterangepicker/moment.min.js"></script>
	<script src="${ contextPath }/user/loginUser/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${ contextPath }/user/loginUser/js/main.js"></script>

</body>
</html>