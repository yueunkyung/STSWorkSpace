<%@page import="com.shinhan.dto.DeptVO"%>
<%@page import="java.util.List"%>
<%@page import="com.shinhan.dto.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<EmpVO> mlist = (List<EmpVO>)request.getAttribute("mlist");
DeptVO dept = (DeptVO)request.getAttribute("dept");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 상세 보기</title>
</head>
<body>
<h1>부서정보</h1>
<!-- (DeptVO)request.getAttribute("dept").getDepartment_id() -->
<form action="deptUpdate.do" method="post">
	부서 번호 : <input type="number" name="department_id" value="${dept.department_id}" readonly="readonly" /><br>
	부서 이름 : <input type="text" name="department_name" value="${dept.department_name}" /><br>
	매니저 : 
	<select name="manager_id">
			<%
			for (EmpVO emp : mlist) {
			%>
			<option 
			<%= dept.getManager_id() == emp.getEmployee_id()? "selected":""%>
			value="<%=emp.getEmployee_id()%>">
				<%=emp.getEmployee_id()%>,
				<%=emp.getFirst_name()%>,
				<%=emp.getLast_name()%>
			</option>
			<%}%>
	</select><br>
	지역 번호 : <input type="number" name="location_id" value="${dept.location_id}" /><br>
	<input type="submit" value="부서 정보 수정" />
</form>
</body>
</html>