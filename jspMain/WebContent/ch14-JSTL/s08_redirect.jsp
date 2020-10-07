<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>

<%-- redirect하면 이 jsp파일의 html이 아예 전송되지 않음 --%>
<%-- 일반적으로 response.sendRedirect를 많이 씀 --%>
<c:redirect url="s01_set.jsp"/>