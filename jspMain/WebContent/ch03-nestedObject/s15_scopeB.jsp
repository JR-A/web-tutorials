<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%-- s14_scopeA.jsp의 기본객체와 공유되는지 확인 --%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 4개 기본 객체와 영역</title>
</head>
<body>
<!-- 각 page마다 전용 pageContext생성하므로 scopeA의 pageContext에 접근불가 -->
page 영역의 msg1 = <%= pageContext.getAttribute("msg1") %><br>	 <%-- 공유되지 않음(null) --%>

<!-- 각 page마다 전용 request생성하므로 scopeA의 request에 접근불가. but 확장가능! -->
request 영역의 msg2 = <%= request.getAttribute("msg2") %><br> <%-- 공유되지 않음(null) --%>

<!-- 브라우저당 session이 생성되므로, 같은 브라우저라면 브라우저가 열려있는동안 공유 가능 -->
session 영역의 msg3 = <%= session.getAttribute("msg3") %><br> <%-- 공유됨 --%>
</body>
</html>