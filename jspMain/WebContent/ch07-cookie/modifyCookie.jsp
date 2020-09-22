<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>	<%-- 인코딩 --%>

<%-- makeCookie.jsp > viewCookies.jsp > modifyCookie.jsp > viewCookies.jsp > deleteCookie.jsp > viewCookie.jsp 순서로 실행  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>값 변경</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(int i=0; i<cookies.length; i++){
			//"name"이라는 식별자의 쿠키가 존재하는지 확인
			if(cookies[i].getName().equals("name")){
				//쿠키명(식별자)이 같은 쿠키 객체를 생성
										  //쿠키명	쿠키값
				Cookie cookie = new Cookie("name", URLEncoder.encode("JSP프로그래밍", "UTF-8"));
				//클라이언트로 생성된 쿠키 전송
				response.addCookie(cookie); //같은 식별자의 쿠키에 덮어씌우기
				
				out.println("name 쿠키의 값을 변경합니다.");
			}
		}
	} else{
		out.println("쿠키가 존재하지 않습니다."); //makeCookie.jsp 먼저 실행해야함
	}
%>
</body>
</html>