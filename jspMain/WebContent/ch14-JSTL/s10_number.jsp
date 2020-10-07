<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%--jsp페이지에 jstl의 fmt 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formatting 라이브러리 - formatNumber 태그</title>
</head>
<body>
숫자 : <fmt:formatNumber value="10000" type="number"/><br>
통화 : <fmt:formatNumber value="10000" type="currency" currencySymbol="\\"/><br>
퍼센트 : <fmt:formatNumber value="0.1" type="percent"/><br>
패턴 : <fmt:formatNumber value="12.345" pattern="00.00"/><br>
</body>
</html>