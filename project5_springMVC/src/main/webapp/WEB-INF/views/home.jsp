<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8">
</head>
<body>
<h1>
	Hello world!  
</h1>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<form action="${path}/day1/hello3" method="post">
	<input type="text" name="userid" value="DD" />
	<input type="submit" value="서버전송" />
</form>
<hr>
<h1>요청문서 파라메터 연습</h1>
<form action="${path}/day1/hello6">
	아이디: <input type="text" name="userid" value="good" />
	비밀번호: <input type="password" name="userpass" value="1234" />
	이메일: <input type="text" name="email2" value="aa@aa.vv" />
	<input type="submit" value="서버전송" />
</form>
<h1>파마메터 전달하기</h1>
<form action="${path}/custInfo">
	custid: <input type="text" name="custid" value="custid" />
	custname: <input type="text" name="custname" value="은경" />
	<input type="submit" value="서버전송" />
</form>
<c:forEach begin="1" end="10" step="1" var="su">
	<p>su는 ${su}</p>
</c:forEach>
<P>  The time on the server is ${serverTime}. </P>
<P>  나의 이름은 ${myname}. </P>
</body>
</html>
