<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/ch05-errorPage/error/viewErrorMessage.jsp" %> <%-- 에러 페이지 지정 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>parameter 출력</title>
</head>
<body>
name 파라미터의 값 = <%= request.getParameter("name").toUpperCase() %> <%-- java.lang.NullPointerException 발생 --%>
</body>
</html>