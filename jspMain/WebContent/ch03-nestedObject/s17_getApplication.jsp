<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 기본 객체 속성 보기</title>
</head>
<body>
<%
	//브라우저를 모두 끈 상태에서도 공유 가능함. 클라이언트와 관계 없이 하나의 웹 어플리케이션과 관련된 영역
	//서버만 동작중이라면 공유 가능. 클라이언트 식별이 불가한 문제
	//설정정보, 변치않는 정보 관리시 사용. 서버를 껐다 켜면(restart) 초기화
	
	//application에 저장된 모든 속성명 반환
	Enumeration attrEnum = application.getAttributeNames();
	
	while(attrEnum.hasMoreElements()){
		String name = (String)attrEnum.nextElement();
		Object value = application.getAttribute(name);
%>
		application 속성 : <b><%= name %></b> = <%= value %><br>
<%
	}
%>
</body>
</html>