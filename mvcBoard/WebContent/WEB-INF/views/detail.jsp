<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h2>글 상세 조회</h2>
	<ul>
		<li>글 번호 : ${boardVO.num}</li>
		<li>제목 : ${boardVO.title}</li>
		<li>작성자 : ${boardVO.name}</li>
	</ul>
	<%-- 수평선 size:두께, width:너비, noshade="noshade":평면적으로 --%>
	<hr size="1" width="100%" noshade="noshade">
	<p>
		${boardVO.content}
	</p>
	<div class="align-right">
		작성일 : ${boardVO.reg_date}								<%-- get방식으로 글번호 전달 --%>
		<input type="button" value="수정" onclick="location.href='modifyForm.do?num=${boardVO.num}'">
		<input type="button" value="삭제" onclick="location.href='deleteForm.do?num=${boardVO.num}'">
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>

</div>
</body>
</html>