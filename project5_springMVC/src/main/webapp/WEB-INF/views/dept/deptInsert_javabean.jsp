<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 부서 등록</title>
</head>
<body>
<h1>부서입력</h1>
<!-- (DeptVO)request.getAttribute("dept").getDepartment_id() -->
<form action="../views/beanTest.jsp" method="get">
	부서 번호 : <input type="number" name="department_id" /><br>
	부서 이름 : <input type="text" name="department_name" /><br>
	매니저 : <input type="text" name="manager_id" /><br>
	지역 번호 : <input type="number" name="location_id" /><br>
	<input type="submit" value="신규 부서 등록" />
</form>
</body>
</html>