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
	<title>Board List</title>
</head>
<body>
	<div>
		<form action="/searchBoard" method="get">
			<input type="hidden" name="limit" value="${limit}" /> <input
				type="hidden" name="pageNum" value="${pageNum}" /> <input
				type="text" name="searchKeyword"
				value="${searchKeyword == null ? '': searchKeyword}" />
			<button>검색</button>
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list}" varStatus="status">
				<tr>
					<td><a href="/seeBoard?id=${b.id}">${b.title}</a></td>
					<td>${b.writer}</td>
					<td>${b.create_date}</td>
					<td>${b.hits}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="page">
		<c:forEach begin="${start}" end="${end}" step="1" var="i">
			<a href="/searchBoard?limit=${limit}&searchKeyword=${searchKeyword}&pageNum=${i}">${i}</a>
		</c:forEach>
	</div>
</body>
</html>
