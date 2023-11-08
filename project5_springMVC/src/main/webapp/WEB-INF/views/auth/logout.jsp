<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<c:set var="appPath" scope="application" value="${pageContext.request.contextPath}" />

<!-- bootstrap : 세계적으로 가장 유명한 CSS framework -->
<!-- CDN(Content Delivery Network) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<!-- 절대경로 - 로그아웃시 페이지가 제각기 달라서(공통사용으로 페이지가 다름) 절대경로로 설정 -->
<img 
	width="100"
	height="100"
	src="${appPath}/image/watch.jpg" />
<a href = "${appPath}/auth/logout.do">로그아웃</a>
<p>${empInfo==null?"게스트":empInfo.first_name}님 환영합니다.</p>
<c:if test="${empInfo==null}">
	<p>고객님 로그인은 필수입니다.</p>
</c:if>
<c:if test="${empInfo!=null}">
	<p>${empInfo.first_name}님 반갑습니다.</p>
</c:if>
<hr>

<!-- HTML 주석 -->
<%-- JSP 주석 ${}--%>
<%
//자바 주석
%>

<%-- 
request : <%= request.getAttribute("empInfo") %><br><br>
session : <%= session.getAttribute("empInfo") %><br><br>
session : ${empInfo}<br><br>
--%>
