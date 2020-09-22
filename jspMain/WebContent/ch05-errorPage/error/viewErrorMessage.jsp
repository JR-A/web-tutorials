<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%-- readParameter.jsp 에서 실행 --%>

<%-- 사용자 정의 에러 페이지 작성 --%>
<%@ page isErrorPage = "true" %> <%-- 에러페이지로 인식됨 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br>
빠른 시간 내에 문제를 해결하도록 하겠습니다.
<p> <%-- 일반적으로 예외정보 표시하지 않는 것이 좋음 --%>
	에러 타입 : <%= exception.getClass().getName() %><br>
	에러 메시지 : <b><%= exception.getMessage() %></b>
</p>
</body>
</html>