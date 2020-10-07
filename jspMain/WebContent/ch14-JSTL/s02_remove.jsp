<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String city = "서울";
%>
<c:set var="str" value="<%= city %>" /> <%--page영역에 저장--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>remove 태그(설정된 속성을 제거)</title>
</head>
<body>
데이터를 삭제하기 전 : ${str}<br>

<c:remove var="str"/>
--------------------------<br>
데이터를 삭제한 후 : ${str}
</body>
</html>