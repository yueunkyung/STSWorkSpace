<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<link href="${cpath}/css/login.css" type="text/css"
	rel="stylesheet" />
<title>알뜰교통카드 로그인</title>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${cpath}/css/fontStyle.css"
	type="text/css" />
<link rel="icon" href="${cpath}/image/알뜰아이콘.png"
	type="image/x-icon" />
	
<style>
   .bxs-user, .bxs-lock-alt {
       font-size: 2em; /* 원하는 크기로 수정, 예: 2em은 2배 크기를 의미 */
   }
</style>
<script>
	var message = "${message}";
	if(message!="") {
		alert(message);
	}
</script>
</head>
<body>

	<div id="container" class="container">
		<div class="row">
			<!-- SIGN UP -->

			<div class="col align-items-center flex-col sign-up">
				<div class="form-wrapper align-items-center">
					<form action="${cpath}/auth/signUp.do" method="post">
						<div class="form sign-up">
							<div class="input-group">
								<i class='bx bxs-user'></i> <input type="text"
									placeholder="cust_id" name="cust_id">
							</div>
							<div class="input-group">
								<i class='bx bxs-user'></i> <input type="text"
									placeholder="cust_name" name="cust_name">
							</div>
							<div class="input-group">
								<i class='bx bx-mail-send'></i> <input type="email"
									placeholder="Email" name="email">
							</div>
							<div class="input-group">
								<i class='bx bxs-lock-alt'></i> <input type="password"
									placeholder="Password" name="password">
							</div>
							<div class="input-group">
								<i class='bx bxs-lock-alt'></i> <input type="text"
									placeholder="전화번호" name="phone">
							</div>
							<input type="hidden" name="address2" value="마포구" />
							<button type="submit" class="btn btn-primary">
								Sign up
							</button>
							<p>
								<span> Already have an account? </span> <b onclick="toggle()"
									class="pointer"> Sign in here </b>
							</p>
						</div>
					</form>
				</div>

			</div>

			<!-- END SIGN UP -->
			<!-- SIGN IN -->

			<div class="col align-items-center flex-col sign-in">
				<div class="form-wrapper align-items-center">
					<form action="${cpath}/auth/loginCheck.do" method="post">
						<div class="form sign-in">
							<div class="input-group">
								<i class='bx bxs-user'></i> 
								<input name="cust_id" required="required"
									type="text" placeholder="고객번호입력">
							</div>
							<div class="input-group">
								<i class='bx bxs-lock-alt'></i> 
								<input name="password" required="required" type="password"
									placeholder="비밀번호입력">
							</div>
							<button>로그인</button>


							<p>
								<span> 계정이 없으신가요? </span> <br> <br> <b onclick="toggle()"
									class="pointer"> 회원가입 </b>
							</p>
						</div>
						</form>
				</div>
			</div>

			<!-- END SIGN IN -->
		</div>

		<!-- END FORM SECTION -->
		<!-- CONTENT SECTION -->
		<div class="row content-row">
			<!-- SIGN IN CONTENT -->
			<div class="col align-items-center flex-col">
				<div class="text sign-in">
					<h2>
						알뜰교통카드 마일리지 <br> dbrud's
					</h2>

				</div>
				<div class="img sign-in"></div>
			</div>
			<!-- END SIGN IN CONTENT -->
			<!-- SIGN UP CONTENT -->
			<div class="col align-items-center flex-col">
				<div class="img sign-up"></div>
				<div class="text sign-up">
					<h2>Join with us</h2>

				</div>
			</div>
			<!-- END SIGN UP CONTENT -->
		</div>
</div>
		<script type="text/javascript" src="${cpath}/js/login.js"></script>
</body>
</html>