<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL의 core라이브러리 사용하기 위해 링크 추가 --%>
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<form id="search_form" action="list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">	<%-- 콤보박스 --%>
					<option value="1">제목</option>
					<option value="2">작성자</option>
					<option value="3">내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" size="16">
			</li>
			<li>
				<input type="submit" value="찾기">
			</li>
		</ul>
	</form>
	
	<div class="list-space align-right">
		<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
			<%-- 로그인 되어있지 않으면(세션의 user_num이 비어있으면) 글쓰기 버튼 비활성화 --%>
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
		>
		<input type="button" value="목록" onclick="location.href='list.do'">
		<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	
	<c:if test="${count == 0}">	<%-- count(전체 글 개수)가 0이면 메시지 표시 --%>
	<div class="result-display">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	
	<%--<c:if test="${!empty list}">--%>	<%-- list가 비어있지 않으면 테이블 표시 --%>
	<c:if test="${count > 0}">		<%-- count(전체 글 개수)가 1개 이상이면 게시글목록 테이블 표시 --%>
	<table>
		<tr>
			<th>글 번호</th>
			<th width="300px">제 목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">	<%-- list의 board객체정보를 차례로 출력 --%>
		<tr>
			<td>${board.board_num}</td>
			<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td> <%-- get방식으로 primary key넘겨줌 --%>
			<td>${board.id}</td>
			<td>${board.reg_date}</td>
			<td>${board.hit}</td>
		</tr>
		</c:forEach>
	</table>
	
	<div class="align-center">
		${pagingHtml}	<%-- 페이지 표시 문자열 --%>
	</div>
	</c:if>
	
</div>
<%-- sessionScope에서 user_num객체 받아옴 --%>