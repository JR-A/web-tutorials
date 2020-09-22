<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer = "1kb" autoFlush="true" %> <!-- 기본값 8kb -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>autoFlush 속성값 true 예제</title>
</head>
<body>
<!-- 대략 4kb크기의 데이터 -->
<%
	for(int i=0; i<1000; i++){
%>
		1234
<%
	}
%>
</body>
</html>