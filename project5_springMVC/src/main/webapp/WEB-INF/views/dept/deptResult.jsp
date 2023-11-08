<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 3초가 지나고 deptList.do로 돌아간다 -->
<meta http-equiv="refresh" content="3;url=deptList.do">
<title>Insert title here</title>
</head>
<body>
	<h1>부서 수정</h1>
	<h2>결과 : ${message}</h2>
	<table border="1">
		<tr>
			<th>부서코드</th>
			<th>부서이름</th>
			<th>매니저번호</th>
			<th>위치코드</th>
		</tr>
		<tr>
			<!--  
			 ((DeptVO)request.getAttribute("dept")).getDepartment_id()
			이런 기능을 하는 문법들을 아래에서 써준 것이다.-->
			<td><input type="number" name="department_id"
				value="${param.department_id}"></td>
			<td><input type="text" name="department_name"
				value="${param.department_name}"></td>
			<td><input type="number" name="manager_id"
				value="${param.manager_id}"></td>
			<td><input type="number" name="location_id"
				value="${param.location_id}"></td>
		</tr>

	</table>
</body>
</html>