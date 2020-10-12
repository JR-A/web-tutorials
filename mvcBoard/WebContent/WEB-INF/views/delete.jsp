<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>      
<c:if test="${check == true}">	<%-- 비밀번호 인증 성공한경우 --%>
	<script>
		alert('글 삭제를 완료했습니다.');
		location.href='list.do';
	</script>
</c:if>
<c:if test="${check == false}">	<%-- 비밀번호 인증 실패한경우 --%>
	<script>
		alert('비밀번호가 일치하지 않습니다.');
		history.go(-1);	 <%--전 페이지로 이동--%>
	</script>
</c:if>