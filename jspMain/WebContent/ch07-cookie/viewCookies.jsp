<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>	<%-- 디코딩 --%>

<%-- makeCookie.jsp > viewCookies.jsp > modifyCookie.jsp > viewCookies.jsp > deleteCookie.jsp > viewCookie.jsp 순서로 실행  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 목록</title>
</head>
<body>
쿠키 목록<br>
<%
	//클라이언트로부터 전송된 쿠키 정보를 반환
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(int i=0; i<cookies.length; i++){
%>
			<b><%= cookies[i].getName() %></b> = <%= URLDecoder.decode(cookies[i].getValue(), "UTF-8") %><br>
<%
		}
	}else{
%>
		쿠키가 존재하지 않습니다. <%-- makeCookie.jsp 먼저 실행하지 않은 경우(쿠키를 생성하지 않은경우) --%>
<%
	}
%>
</body>
</html>