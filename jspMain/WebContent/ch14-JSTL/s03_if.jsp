<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if 태그(조건식에 해당하는 블럭과 사용될 scope설정. 단일 조건 체크)</title>
</head>
<body>

<%-- if 태그는 단일 조건 체크임!! --%>
<%-- cf) 다중 조건 체크는 choose-when-otherwise태그 --%>

<c:if test="true">
	무조건 실행<br>
</c:if>

<%-- get방식으로 테스트 --%>
<%-- http://localhost:8080/jspMain/ch14-JSTL/s03_if.jsp?name=dragon --%>
<c:if test="${param.name == 'dragon'}">	<%-- EL에서는 문자열 비교시 ==연산자 사용 (톰캣 상위버전은 equals메소드 사용 가능) --%>
	name 파라미터의 값이 ${param.name} 입니다.<br>
</c:if>

<%-- Tomcat 7 버전 이상부터 equals 비교 지원 --%>
<c:if test="${param.name.equals('dragon')}">
	name 파라미터의 값이 ${param.name} 입니다.<br>
</c:if>

<%-- get방식으로 테스트 --%>
<%-- http://localhost:8080/jspMain/ch14-JSTL/s03_if.jsp?age=20 --%>
<c:if test="${param.age >= 20}">
	당신의 나이는 20세 이상입니다.
</c:if>

<%-- get방식으로 여러 parameter 넘기려면 & 사용 --%>
<%-- http://localhost:8080/jspMain/ch14-JSTL/s03_if.jsp?age=20&name=dragon --%>
</body>
</html>