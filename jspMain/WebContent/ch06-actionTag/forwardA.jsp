<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
	//전송된 데이터 인코딩 처리 (인코딩 위치에 주의. 맨 처음 access하는 페이지에서 인코딩!)
	request.setCharacterEncoding("utf-8"); 
%>
  
  <%-- 주소는 그대로, 내용만 변경됨 --%> <%-- redirect는 주소를 변경 --%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포워드 테스트</title>
</head>
<body>
forwardA.jsp 페이지. 보여지지 않습니다.
</body>
</html>
<jsp:forward page="forwardB.jsp">
	<jsp:param value="오렌지" name="color"/>
</jsp:forward>