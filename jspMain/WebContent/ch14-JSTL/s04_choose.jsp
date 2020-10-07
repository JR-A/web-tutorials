<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>choose 태그(다중 조건 체크)</title>
</head>
<body>
<%-- get방식으로 테스트. 여러 parameter 넘기려면 & 사용 --%>
<%-- http://localhost:8080/jspMain/ch14-JSTL/s03_if.jsp?age=20&name=dragon --%>

<%-- choose 태그는 다중 조건 체크(choose~when~otherwise) --%>
<c:choose>
	<c:when test="${param.name == 'dragon' && param.age >= 20 }">
		당신의 이름은 ${param.name}이고 20세 이상입니다.
	</c:when>
	<c:when test="${param.name == 'dragon'}">
		당신의 이름은 ${param.name}입니다.
	</c:when>
	<c:when test="${param.age >=20 }">
		당신의 나이는 20세 이상입니다.
	</c:when>
	<c:otherwise>
		당신의 이름은 dragon이 아니고 20세 이상이 아닙니다.
	</c:otherwise>
</c:choose>
</body>
</html>