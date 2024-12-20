<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"
	value="${ pageContext.servletContext.contextPath }" scope="session" />
	
<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="${ contextPath }/login/css/style.css">

	</head>
	<body class="img js-fullheight" style="background-image: url(${ contextPath }/login/images/bg.jpg);">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
				      	<h3 class="mb-4 text-center">Have an account?</h3>
				      	<form action="" class="signin-form" method="post">
				      		<div class="form-group">
				      			<input type="text" name="email" value="${ !empty email ? email : '' }" class="form-control" placeholder="Email" required>
				      		</div>
				            <div class="form-group">
				              <input name="password" id="password-field" type="password" value="${ !empty password ? password : '' }" class="form-control" placeholder="Password" required>
				              <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
				            </div>
				            <div class="form-group">
				            	<button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
				            </div>
			          </form>
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
		      		</div>
				</div>
			</div>
		</div>
	</section>

	<script src="${ contextPath }/login/js/jquery.min.js"></script>
  <script src="${ contextPath }/login/js/popper.js"></script>
  <script src="${ contextPath }/login/js/bootstrap.min.js"></script>
  <script src="${ contextPath }/login/js/main.js"></script>

	</body>
</html>

