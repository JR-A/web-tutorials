<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 속성 지정</title>
</head>
<body>
<%
	String name = "userId";
	String value = "dragon";
	
	//application에 값 저장
						    //속성명  속성값
	application.setAttribute(name, value);
%>

application 기본 객체의 속성 설정 :
<%= name %> = <%= value %>

</body>
</html>