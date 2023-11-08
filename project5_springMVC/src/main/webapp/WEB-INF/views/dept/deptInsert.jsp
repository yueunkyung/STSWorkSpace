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
<h1>부서입력</h1>
<!-- (DeptVO)request.getAttribute("dept").getDepartment_id() -->
<form action="deptInsert.do" method="post">
	부서 번호 : <input type="number" name="department_id" /><br>
	부서 이름 : <input type="text" name="department_name" /><br>
	매니저 : <select name="manager_id">
					<%
					for (EmpVO emp : mlist) {
					%>
					<option value="<%=emp.getEmployee_id()%>">
						<%=emp.getFirst_name()%>
						<%=emp.getLast_name()%>
					</option>
					<%
					}
					%>
			</select><br>
	지역 번호 : <input type="number" name="location_id" /><br>
	<input type="submit" value="신규 부서 등록" />
</form>
</body>
</html>