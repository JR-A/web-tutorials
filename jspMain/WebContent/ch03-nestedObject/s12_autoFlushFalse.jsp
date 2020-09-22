<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="1kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>autoFlush 속성값 false 예제</title>
</head>
<body>
<!-- 
	java.io.IOException: 행 [14]에서 [/ch03-nestedObject/s12_autoFlushFalse.jsp]을(를) 처리하는 중 예외 발생
	java.io.IOException: 오류: JSP 버퍼 오버플로우
 -->
<%
	for(int i=0; i<1000; i++){
%>
		1234
<%		
	}
%>
</body>
</html>