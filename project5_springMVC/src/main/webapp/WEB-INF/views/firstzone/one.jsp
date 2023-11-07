<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>One.jsp</h1>
	<ul>
		<li>${myname}</li>
		<li>${myage}</li>
		<li>${mycar.model}</li>
		<li>${mycar.price}</li>
		<li>세션정보: ${login_id}</li>
		<li>세션정보: ${login_phone}</li>
	</ul>
</body>
</html>