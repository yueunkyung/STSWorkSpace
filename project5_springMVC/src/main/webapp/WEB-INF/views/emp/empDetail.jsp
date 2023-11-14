<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<h1>~직원 상세보기~</h1>
		<form name="empform" action="${appPath}/emp/empDetail.do" method="post">
			<c:if test="${emp.pic!=null}">
				<div style="max-height:300px;height:100%;display:flex; justify-content:center;">
					<img src="${appPath}/uploads/${emp.pic}" alt="${emp.pic}" style="display:block;">
				</div>
			</c:if>
			<fieldset>
				<legend>직원의 기본사항</legend>
				<ul>
					<li><span>1.EMPLOYEE_ID:</span><input name="employee_id"
						type="number" value="${emp.employee_id}"  /></li>
					<li><span>2.FIRST_NAME:</span><input name="first_name"
						type="text" placeholder="이름/first name"
						value="${emp.first_name}"  /></li>
					<li><span>3.LAST_NAME:</span><input name="last_name"
						type="text" placeholder="성/last name"
						value="${emp.last_name}"  /></li>
				</ul>
			</fieldset>
			<fieldset>
				<legend>직원의 추가정보</legend>
				<ul>
					<li>
						<span>4.EMAIL:</span><input name="email" type="text"
						required value="${emp.email}"
						 />
					</li>
					<li>
						<span>5.PHONE_NUMBER:</span>
						<input name="phone_number"
						type="tel" pattern="[0-9]{3}.[0-9]{3}.[0-9]{4}"
						placeholder="***.***.****" value="${emp.phone_number}"
						 />
					</li>
					<li>
						<span>6.HIRE_DATE:</span><input name="hire_date"
						type="date" value="${emp.hire_date}" />
					</li>
					<li>
						<span>7.JOB_ID:</span>
						<select name="job_id" >
							<c:forEach items="${jlist}" var="job">
							<option value="${job.job_id}" ${emp.job_id==job.job_id?"selected" : ""}>
								${job.job_title}--${job.max_salary}
							</option>
							</c:forEach>
						</select>
					</li>
					<li><span>8.SALARY:</span><input name="salary" type="number"
						placeholder="급여 정보" value="${emp.salary}"  /></li>
					<li><span>9.COMMISSION_PCT:</span><input name="commission_pct"
						type="number" step="0.01" value="${emp.commission_pct}"
						 /></li>
					<li>
						<span>10.MANAGER_ID:</span>
						<select name="manager_id"
							>
							<c:forEach items="${mlist}" var="manager">
								<option value="${manager.employee_id}"
									${emp.manager_id==manager.employee_id?"selected" : ""}>
									${manager.first_name}, ${manager.last_name}
								</option>
							</c:forEach>
						</select>
					</li>
					<li>
					<span>11.DEPARTMENT_ID:</span> 
					<select name="department_id" >
						<c:forEach items="${dlist}" var="dept">
							<option value="${dept.department_id}"
									${emp.department_id==dept.department_id?"selected" : ""}>
								${dept.department_name}
							</option>
						</c:forEach>
					</select>
					</li>
				</ul>
				<div class="btn_wrap">
					<button class="btn btn-success" type="submit">수정하기♥</button>
					<input type="reset" class="btn btn-success" value="Reset Button" />
					<button type="button" class="btn btn-success">삭제</button>
				</div>
			</fieldset>
		</form>
	</div>
	<!--//END_container-->
</body>

</html>