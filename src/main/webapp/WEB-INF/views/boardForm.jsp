<!-- 임포트 목록 -->
<%@page import="java.util.List"%>
<%@page import="com.study.app.Board"%>

<!-- 페이지 옵션 -->
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta charset='utf-8'>
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>Board Form</title>
</head>
<body>
<form action="/editBoard" method="post">
	
	<div>
		<input type="hidden" name="id" value="${detail.id}" />
		<div>작성자: <input type="text" name="writer" value="${detail.writer}" /> </div>
		<div>등록일자: ${detail.create_date}</div>
	</div>
	<div>
		<h2><input type="text" name="title" value="${detail == null ? '' : detail.title}"/></h2>
	</div>
	<div>
		<h2><input type="text" name="content" value="${detail == null ? '' : detail.content}"/></h2>
	</div>
	<div>
		<a href="/searchBoard?limit=10&pageNum=1">목록</a>
		<button>저장</button>
	</div>
</form>
</body>
</html>
