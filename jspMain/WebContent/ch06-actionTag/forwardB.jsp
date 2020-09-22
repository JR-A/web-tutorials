<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String color = request.getParameter("color");
%>

<%-- forwardA.jsp 에서 실행(주소는 그대로이나 내용만 forwardB.jsp로 변경) --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forwardB.jsp</title>
</head>
<body>
forwardB.jsp 페이지<br>
request에 파라미터로 전송된 데이터 = <%= color %>
</body>
</html>