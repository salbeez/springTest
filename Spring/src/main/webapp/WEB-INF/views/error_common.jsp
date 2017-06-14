<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 메시지 창</title>
</head>
<body>
	<h3>${exception.getMessage() }</h3>

	<ul>
		<c:forEach var="stack" items="${exception.getStackTrace() }">
			<li>${stack.toString() }</li>
		</c:forEach>
	</ul>
</body>
</html>