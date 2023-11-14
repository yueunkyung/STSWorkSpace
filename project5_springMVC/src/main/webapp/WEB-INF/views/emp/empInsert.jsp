
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap : 세계적으로 가장 유명한 CSS framework -->
<!-- CDN(Content Delivery Network) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	font-family: 'Jeju Gothic', sans-serif;
}

ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

.dis_flex {
	display: flex;
	align-items: center;
	justify-content: center;
}

#container {
	position: relative;
	width: 80%;
	max-width: 800px;
	border: 1px solid gray;
	padding-top: 74px;
	margin: 0 auto;
	border-radius: 15px;
	background: url("../html5-css3-master/07/images/bg1.png");
	background-color: rgba(255, 255, 255, 0.5);
}

h1 {
	position: absolute;
	top: 20px;
	left: 50%;
	margin: 0;
	transform: translateX(-50%);
	background-color: #fff;
	text-align: center;
	font-weight: bold;
}

fieldset {
	padding: 20px;
	margin: 15px;
	background-color: #fff;
	border: 4px solid green;
}

fieldset legend {
	font-weight: 700;
	font-size: 20px;
}

ul li {
	font-size: 14px;
	margin-bottom: 5px;
}

ul li:last-child {
	margin-bottom: 0;
}

ul li:after {
	content: '';
	display: block;
	clear: both;
}

ul li span {
	float: left;
	width: 150px;
	line-height: 35px;
	margin-right: 10px;
}

ul li input, ul li select {
	float: left;
	width: calc(100% - 160px);
	padding: 5px 10px;
	margin: 0;
	box-sizing: border-box;
}

.btn_wrap {
	margin: 30px 0 10px;
	text-align: center;
}
</style>
</head>

<body>
	<%@include file="../auth/logout.jsp" %>
	<div id="container">
		<h1>~직원등록~</h1>
		<form name="empform" action="empInsert.do" method="post" enctype="multipart/form-data">
			<div>
				<%-- uploadFile EmpVO 에 썼던 이름이랑 같아야 함 --%>
				<input type="file" name="uploadFile" />			
			</div>
			<hr>
			<fieldset>
				<legend>직원의 기본사항</legend>
				<ul>
					<li><span>1.EMPLOYEE_ID:</span><input name="employee_id"
						type="number" min="207" max="500" autofocus required /></li>
					<li><span>2.FIRST_NAME:</span><input name="first_name"
						type="text" placeholder="이름/first name" /></li>
					<li><span>3.LAST_NAME:</span><input name="last_name"
						type="text" placeholder="성/last name" /></li>
				</ul>
			</fieldset>
			<fieldset>
				<legend>직원의 추가정보</legend>
				<ul>
					<li><span>4.EMAIL:</span><input name="email" type="text"
						required placeholder="@(골뱅이) 전까지만" /></li>
					<li><span>5.PHONE_NUMBER:</span><input name="phone_number"
						type="tel" placeholder="***.***.****" /></li>
					<li><span>6.HIRE_DATE:</span><input name="hire_date"
						type="date" required /></li>
					<li><span>7.JOB_ID:</span> <select name="job_id" required>
						<c:forEach items="${jlist}" var="job">
							<option value="${job.job_id}">
								${job.job_title}--${job.max_salary}
							</option>
						</c:forEach>
					</select></li>
					<li><span>8.SALARY:</span><input name="salary" type="number"
						placeholder="급여 정보" /></li>
					<li><span>9.COMMISSION_PCT:</span><input name="commission_pct"
						type="number" step="0.01" /></li>
					<li><span>10.MANAGER_ID:</span> <select name="manager_id">
							<option value="0">매니저없음</option>
							<c:forEach items="${mlist}" var="manager">
								<option value="${manager.employee_id}">
									${manager.first_name}, ${manager.last_name}
								</option>
							</c:forEach>
					</select></li>
					<li><span>11.DEPARTMENT_ID:</span> <select
						name="department_id">
						<option value="0">부서없음</option>
						<c:forEach items="${dlist}" var="dept">
							<option value="${dept.department_id}">
								${dept.department_name}
							</option>
						</c:forEach>
					</select></li>
				</ul>
				<div class="btn_wrap">
					<button class="btn btn-success" type="submit">등록하기♥</button>
					<input type="reset" class="btn btn-success" value="Reset Button">
				</div>
			</fieldset>
		</form>
	</div>
	<!--//END_container-->
</body>

</html>