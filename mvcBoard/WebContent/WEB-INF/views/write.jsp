<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h1>글쓰기 완료</h1>
	<div class="result-display">
		게시판에 글 등록을 완료했습니다.<br>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
</body>
</html>