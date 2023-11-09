<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 상세 보기</title>
</head>
<body>
<%@include file="../auth/logout.jsp" %>
<h1>부서정보</h1>
<!-- (DeptVO)request.getAttribute("dept").getDepartment_id() -->
<form action="${appPath}/dept/deptUpdate.do" method="post">
	부서 번호 : <input type="number" name="department_id" value="${dept.department_id}" readonly="readonly" /><br>
	부서 이름 : <input type="text" name="department_name" value="${dept.department_name}" /><br>
	매니저 : 
	<select name="manager_id">
		<c:forEach items="${mlist}" var="emp">
			<option ${dept.manager_id == emp.employee_id? "selected":""} value="${emp.employee_id}">
				${emp.employee_id}, ${emp.first_name}, ${emp.last_name}, ${dept.manager_id}
			</option>
		</c:forEach>
	</select>
	<br>
	지역 번호 : <input type="number" name="location_id" value="${dept.location_id}" /><br>
	<input type="submit" value="부서 정보 수정" />
</form>
</body>
</html>