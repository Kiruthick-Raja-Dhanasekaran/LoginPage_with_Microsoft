<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" href="images/favicon_io/favicon.ico" />
<!DOCTYPE html>
<html>
<head>

<!-- Page Content -->
<link id="contextPathHolder"
	data-contextPath="${pageContext.request.contextPath}" />

<title>Login</title>
<!-- scripts and stylesheets -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/login-styles.css" rel="stylesheet" type="text/css">
</head>


<body class="login-page">

	<!-- Section: Design Block -->
	<section class="text-center">
		<!-- Background image -->
		<div class="p-5 login-bg-image">
			<div class=container>
				<a class="navbar-brand login-logo-link" href="#">
					<img src="${pageContext.request.contextPath}/images/bajajlogo.png" height="80px" class="d-inline-block align-centre" alt="Company_logo" />
				</a>
			</div>
		</div>
		<!-- Background image -->
		<div class="card mx-4 mx-md-5 shadow-5-strong login-card">
			<div class="card-body py-5 px-md-5 login-card-body">
				<div class="row d-flex justify-content-center">
					<div class="col-lg-8">
						<h2 class="fw-bold mb-5">Login</h2>
						<!-- logic for invalid credentials or exception -->
						<%-- 	<c:choose>
							<c:when test="${not empty msg}">
								<p style="color: red">${msg}</p>
							</c:when>
						</c:choose> --%>
						<c:if test="${not empty sessionScope.errorMessage}">
							<p class="error-message">
								<c:out value="${sessionScope.errorMessage}" />
							</p>
							<%
							session.removeAttribute("errorMessage");
							%>
						</c:if>
						<!-- Error message display -->
						<div id="errorMessage" class="error-message">
							Invalid credentials. Please try again.</div>
						<!-- logic for invalid credentials or exception -->
						<!-- Error message display -->
						<div id="sessionExpiredMessage" class="error-message">
							Session Expired. Login again.</div>
						<!-- logic for invalid credentials or exception -->
						<!-- form for adid and password-->
						<form action="${pageContext.request.contextPath}/api/login"
							method="post" onsubmit="return mySubmitFunction(event)">
							<!-- adid input -->
							<div class="form-outline mb-4">
								<label for="username" class="sr-only">Username</label>
								<input type="text" class="form-control" name="username"
									id="username" placeholder="Username" />
							</div>
							<!-- Password input -->
							<div class="form-outline mb-4">
								<label for="password" class="sr-only">Password</label>
								<input type="password" id="password" name="password"
									class="form-control" placeholder="Password" />
							</div>
							<!-- Submit button -->
							<button type="submit" id="do-call"
								class="btn btn-primary btn-block mb-4">Sign in</button>
							<!--  Spinner -->

						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="spanner">
			<div class="loader"></div>
		</div>
	</section>
	<!-- Section: Design Block -->
</body>
<script>
	//on click for spinner and exception handling
	$(document).ready(function() {
		$("#do-call").click(function() {
			$("div.spanner").addClass("show");
			console.log($("div.spanner"));
		});
	});

	function mySubmitFunction() {
		const username = document.getElementById("username").value;
		const password = document.getElementById("password").value;
		if (password == "" || username == "") {
			Swal.fire({
				icon : 'error',
				title : 'Oops...',
				text : 'Please enter the Username and Password',
			})
			$("div.spanner").removeClass("show");
			return false;
		}
		return true;
	}
	const urlParams = new URLSearchParams(window.location.search);
	const error = urlParams.get('error');
	if (error === 'true') {
		document.getElementById('errorMessage').style.display = 'block';
		//document.getElementById('errorMessage').style.padding-bottom = 10px;
	} else if (error === 'sessionExpired') {
		document.getElementById('sessionExpiredMessage').style.display = 'block';
		//document.getElementById('errorMessage').style.padding-bottom = 10px;
	}
</script>
</html>