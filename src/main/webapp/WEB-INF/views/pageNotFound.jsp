<!-- 임포트 목록 -->
<%@page import="java.util.List"%>
<%@page import="com.study.app.Board"%>

<!-- 페이지 옵션 -->
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>Error</title>
</head>
<body>
<div>
	<h1><c:out value="${code}"/></h1>
	<p><c:out value="${message}"/></p>
</div>
</body>
</html>
