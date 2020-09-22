<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- jsp설정 -->
<%@ page import="java.util.Date" %>		<!-- [지시자 Directives @] 웹컨테이너가 jsp 페이지를 처리할 때 필요한 정보를 기술 -->
<%@ page import="java.text.SimpleDateFormat" %>
<%											//[스크립트릿 Scriptlet Elements] java 영역. 프로그래밍 코드 기술
	Date nowTime = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 날짜와 시간</title>
</head>
<body>

현재 날짜와 시간은 <%= nowTime %> 입니다.<br>			<!--[표현식 Expressions =] 출력용 java 영역. 화면에 출력한 내용 기술 -->
---------------------------------<br>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>
현재 날짜와 시간은 <%= sf.format(nowTime) %> 입니다.

</body>
</html>