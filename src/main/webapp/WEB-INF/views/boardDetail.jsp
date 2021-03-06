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
	<title>Board Detail</title>
</head>
<body>
	<div>
		<div>작성자: ${detail.writer}</div>
		<div>등록일자: ${detail.create_date}</div>
	</div>
	<div>
		<h2>${detail.title}</h2>
	</div>
	<div>
		<p>${detail.content}</p>
	</div>
	<div>
		<a href="/deleteBoard?id=${detail.id}">삭제</a>
		<a href="/boardEditForm?id=${detail.id}">수정</a>
		<a href="/searchBoard?limit=10&pageNum=1">목록</a>
	</div>
</body>
</html>
