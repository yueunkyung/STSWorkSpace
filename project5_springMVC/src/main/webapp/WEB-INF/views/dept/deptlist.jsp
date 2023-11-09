<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록</title>
<script>
	var message="${deptMessage}";
	if(message!="") alert(message);
</script>
</head>
<body style="margin: 50px 0 100px;">
<%@include file="../auth/logout.jsp" %>
<button onclick="location.href='${appPath}/dept/deptInsert.do';">부서등록</button>
<br>
	<h1>부서 목록</h1>
	<table border="1">
		<tr>
			<th></th>
			<th></th>
			<th>부서코드</th>
			<th>부서이름</th>
			<th>매니저번호</th>
			<th>위치코드</th>
		</tr>
		<c:forEach items="${dlist}" var="dept">
			<tr>
				<td>
					<button onclick="location.href='${appPath}/dept/deptDetail.do?deptid=${dept.department_id}';">상세보기</button>
				</td>
				<td>
					<button onclick="location.href='${appPath}/dept/deptDelete.do?deptid=${dept.department_id}';">삭제</button>
				</td>
				<td>${dept.department_id}</td>
				<td>${dept.department_name}</td>
				<td>${dept.manager_id!=0?dept.manager_id:""}</td>
				<td>${dept.location_id}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>