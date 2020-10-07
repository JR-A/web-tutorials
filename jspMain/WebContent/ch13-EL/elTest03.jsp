<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 예제 3</title>
</head>
<body>
<%
	pageContext.setAttribute("msg1", "봄");
	request.setAttribute("msg2", "여름");
	session.setAttribute("msg3", "가을");
	application.setAttribute("msg4", "겨울");
	
	
	String str = "서울";
	
	pageContext.setAttribute("city", str);
%>
<%--
	el 표기법을 이용하여 JSP 4개 영역에 저장된 데이터를 읽어올 때
	pageScope, requestScope, sessionScope, applicationScope를 이용해 저장된 데이터 읽어올 수 있음
 --%>
 
<%-- 각 영역의 속성명이 같아도 영역명(내장객체)까지 명시한다면 속성명이 unique하지 않아도 됨(ex.각 영역의 속성명이 모두 msg여도 가능) --%>
page scope : ${pageScope.msg1}<br>
request scope : ${requestScope.msg2}<br>
session scope : ${sessionScope.msg3}<br>
application scope : ${applicationScope.msg4}<br>
------------------------<br>
<%-- 내장객체 생략하고 속성명만 명시하여도 인식(속성명이 unique하다는 전제 하에!) --%>
<%-- page영역, request영역, session영역, application영역순으로 속성을 탐색하여 결과 반환 --%>
page scope : ${msg1}<br>
request scope : ${msg2}<br>
session scope : ${msg3}<br>
application scope : ${msg4}<br>
=========================<br>

<%-- el은 일반 변수 읽을 수 없음. JSP 4개 기본영역 중 하나에 저장하여야함 --%>
스크립트릿에서 선언한 변수의 값 읽기 : ${str}<br> <%-- 일반 변수 읽을 수 없음. 빈 문자열로 처리 --%>
page영역에 저장된 값 읽기 : ${city}<br>

</body>
</html>