<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- selectTest.jsp에서 실행하여 글 목록 불러오고, 특정 게시물 선택해서 detailTest.jsp 실행 후 삭제 버튼 클릭 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<%
	int num = Integer.parseInt(request.getParameter("num")); //get방식이므로 인코딩 불필요
%>
<div class="page-main-style">
	<h2>글 삭제</h2>
	<p class="align-center">
		<span>정말 삭제하시겠습니까?</span>
	</p>
	<form action="deleteTest.jsp" method="post">
		<input type="hidden" name="num" value="<%= num %>">
		<div class="align-center">
			<input type="submit" value="삭제">
			<input type="button" value="목록 보기" onclick="location.href='selectTest.jsp'">
		</div>
	</form>
</div>
</body>
</html>