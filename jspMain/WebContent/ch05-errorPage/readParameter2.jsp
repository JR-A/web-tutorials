<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터 출력</title>
</head>
<body>
<%-- web.xml의 변경내용 반영시키기 위해 server restart해야함 --%>
name 파라미터의 값 = <%= request.getParameter("name").toUpperCase() %> <%-- 500 에러 발생(Internal Server Error)-error500.jsp 페이지 호출 --%>
<%-- NullPointerException에러타입을 web.xml파일에 추가하였으므로 우선순위에 따라 viewErrorMessage.jsp 페이지 호출 --%>

<%--http://localhost:8080/jspMain/ch05-errorPage/ddd.jsp 등 존재하지 않는 url로 이동 -> 404 에러 발생(Not Found) --%>

</body>
</html>