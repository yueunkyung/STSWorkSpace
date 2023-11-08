<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<h1>직원목록</h1>
<table>
	<thead>
		<tr>
			<th>순서</th>
			<th>employee_id</th>
			<th>first_name</th>
			<th>last_name</th>
			<th>email</th>
			<th>phone_number</th>
			<th>salary</th>
			<th>job_id</th>
			<th>hire_date</th>
			<th>commition_pct</th>
			<th>manager_id</th>
			<th>department_id</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${emplist}" var="emp" varStatus="status">
			<tr seq="${status.count}">
				<td class="aa">${status.count}</td>
				<td><a href="${cpath}/emp/empDetail.do?empid=${emp.employee_id}">
						${emp.employee_id}
				</a></td>
				<td fname="${emp.first_name}">${emp.first_name}</td>
				<td>${emp.last_name}</td>
				<td>${emp.email}</td>
				<td>${emp.phone_number}</td>
				<td>
					<fmt:formatNumber groupingUsed="true" value="${emp.salary}"></fmt:formatNumber>
				</td>
				<td>${fn:toLowerCase(emp.job_id)}</td>
				<td>${emp.hire_date}</td>
				<td>${emp.commission_pct}</td>
				<td>${emp.manager_id}</td>
				<td>${emp.department_id}</td>
				<td>
					<button type="button" class="btn btn-success"
					onclick="location.href='${cpath}/emp/empDelete.do?empid=${emp.employee_id}'"
					>삭제(method="get")</button>
					 
					<form action="${cpath}/emp/empDelete.do" method="get">
						<input type="hidden" name="empid" value="${emp.employee_id}" />
						<input type="submit" value="삭제(method='post')" class="btn btn-primary" />
					</form>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>