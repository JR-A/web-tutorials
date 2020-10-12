<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL core라이브러리 사용위함 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h2>게시판 목록</h2>	
	<div class="align-right">
											<%-- writeForm.jsp는 list.jsp와 같은 경로의 파일이므로 / 써줄 필요가 없음  --%>
		<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"> <%-- writeForm.jsp가 아닌 writeForm.do임에 유의 web.xml참고 --%>
	</div>
	
	<%-- 아직 페이징 처리는 하지 않았다 --%>
	<%-- list가 null이거나 empty일 경우(list 데이터가 없는 경우) --%>
	<c:if test="${empty list}">
		<div class="result-display">
			등록된 게시물이 없습니다.
		</div>
	</c:if>
	<%-- list가 null도 아니고 empty도 아닌경우(list 데이터가 존재하는 경우) --%>
	<c:if test="${!empty list }">
		<table>
			<tr>
				<th>글 번호</th>
				<th>제 목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<%-- JSTL core라이브러리의 forEach함수 이용하여 request에 저장된 list객체 확장for문 돌리기 --%>
			<c:forEach var="board" items="${list}">
			<tr>
				<%-- board.getNum() 해도 되지만 EL은 원칙적으로 '.'으로 프로퍼티 접근 --%>
				<td>${board.num}</td>
				<td><a href="detail.do?num=${board.num}">${board.title}</a></td>
				<td>${board.name}</td>
				<td>${board.reg_date}</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	
</div>
</body>
</html>