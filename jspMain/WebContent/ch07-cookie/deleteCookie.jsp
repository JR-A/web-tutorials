<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- makeCookie.jsp > viewCookies.jsp > modifyCookie.jsp > viewCookies.jsp > deleteCookie.jsp > viewCookie.jsp 순서로 실행  --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 삭제</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(int i=0; i<cookies.length; i++){
			//"name"이라는 식별자의 쿠키가 존재하는지 확인
			if(cookies[i].getName().equals("name")){
				//쿠키명(식별자)이 같은 쿠키 객체를 생성
				Cookie cookie = new Cookie("name", "");
				//쿠키 정보 제거
				cookie.setMaxAge(0);	//쿠키가 만료됨
				
				//생성된 쿠키를 클라이언트로 전송
				response.addCookie(cookie);  //같은 식별자의 쿠키에 덮어씌우기
				
				out.println("name 쿠키를 삭제합니다.");
			}
		}
	} else{
		out.println("쿠키가 존재하지 않습니다.");
	}
%>
</body>
</html>