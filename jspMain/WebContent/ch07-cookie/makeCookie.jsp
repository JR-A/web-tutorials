<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>	<%-- 인코딩 --%>

<%-- makeCookie.jsp > viewCookies.jsp > modifyCookie.jsp > viewCookies.jsp > deleteCookie.jsp > viewCookie.jsp 순서로 실행  --%>

<%	
	//쿠키 생성				  //쿠키명(식별자)	쿠키값
	Cookie cookie = new Cookie("name",URLEncoder.encode("홍길동", "UTF-8"));

	//쿠키 유효시간을 지정(단위 : 초)
	//쿠키 유효시간을 지정하면 클라이언트 영역에 파일을 생성해서 쿠키 정보 보관 - 브라우저 종료되어도 기간 내면 쿠키 정보 보관됨
	//쿠키 유효시간 미지정시 메모리에 쿠키 정보 보관 - 브라우저 종료시 쿠키 정보 휘발
	
	//cookie.setMaxAge(30*60);	//쿠키 유효시간 지정
	cookie.setMaxAge(-1);	//메모리에 쿠키 정보 보관
	
	//클라이언트에 쿠키 전송
	response.addCookie(cookie);
%>
<%-- 검사 - Application - Cookies로 확인 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 생성</title>
</head>
<body>

<%= cookie.getName() %> 쿠키의 값 : <%=cookie.getValue() %>

</body>
</html>