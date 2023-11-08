<%@page import="com.shinhan.dto.DeptVO"%>
<%@page import="java.util.List"%>
<%@page import="com.shinhan.model.DeptService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<DeptVO> dlist = (List<DeptVO>) request.getAttribute("dlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록</title>
</head>
<body style="margin: 50px 0 100px;">
<button onclick="location.href='deptInsert.do';">부서등록</button>
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
		<%
		for (DeptVO dept : dlist) {
		%>
		<tr>
			<td>
				<button onclick="location.href='deptDetail.do?deptid=<%=dept.getDepartment_id()%>';">상세보기</button>
			</td>
			<td>
				<button onclick="location.href='deptDelete.do?deptid=<%=dept.getDepartment_id()%>';">삭제</button>
			</td>
			<td><%=dept.getDepartment_id()%></td>
			<td><%=dept.getDepartment_name()%></td>
			<td><%=dept.getManager_id()%></td>
			<td><%=dept.getLocation_id()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>