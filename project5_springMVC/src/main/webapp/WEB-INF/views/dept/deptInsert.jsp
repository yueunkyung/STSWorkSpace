<%@page import="com.shinhan.dto.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<EmpVO> mlist = (List<EmpVO>)request.getAttribute("mlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 부서 등록</title>
</head>
<body>
<%@include file="../auth/logout.jsp" %>
<h1>부서입력</h1>
<!-- (DeptVO)request.getAttribute("dept").getDepartment_id() -->
<form action="${appPath}/dept/deptInsert.do" method="post">
	부서 번호 : <input type="number" name="department_id" /><br>
	부서 이름 : <input type="text" name="department_name" /><br>
	매니저 : <select name="manager_id">
				
				<c:forEach items="${mlist}" var="emp">
					<option ${dept.manager_id == emp.employee_id? "selected":""} value="${emp.employee_id}">
						${emp.first_name}, ${emp.last_name}, ${dept.manager_id}
					</option>
				</c:forEach>
			</select>
			<br>
	지역 번호 : <input type="number" name="location_id" /><br>
	<input type="submit" value="신규 부서 등록" />
</form>
</body>
</html>