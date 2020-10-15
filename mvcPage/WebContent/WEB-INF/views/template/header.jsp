<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--JSTL core라이브러리 사용--%>
<h2 class="align-center">회원제 게시판</h2>
<div class="align-right">
	<c:if test="${!empty user_num}"> <%-- user_num이 존재하는 경우 --%>
		[<span>${user_id}</span>]
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	<c:if test="${empty user_num}"> <%-- user_num이 존재하지 않는 경우 --%>
		<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>