<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forTokens 태그(구분자를 기준으로 문자열을 tokenize함 )</title>
</head>
<body>
<%-- StringTokenizer와 유사 --%>
<h4>콤마와 점을 구분자로 사용 : </h4>
<%--		 	속성명		문자열(객체)				구분자  --%>
<c:forTokens var="token" items="빨,주,노.초,파.남,보" delims=",.">
	${token}<br>
</c:forTokens>

<h4>연월일시분초로 출력(구분자를 이용해서 분리)</h4>
<c:forTokens var="now" items="2020-10-07 12:49:10" delims="- :"> <%-- 하이픈(-), 공백( ), 콜론(,)이 모두 구분자 --%>
	${now}<br>
</c:forTokens>
</body>
</html>