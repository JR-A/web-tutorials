<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>functions 라이브러리</title>
</head>
<body>
<c:set var="str1" value=" Functions <태그>를 사용합니다. " />

문자열의 길이:${fn:length(str1)}<br>
대문자로 변경:${fn:toUpperCase(str1)}<br>
소문자로 변경:${fn:toLowerCase(str1)}<br>
인덱스 3부터 ~ 6 전까지 문자열 추출:${fn:substring(str1,3,6)}<br>
문자열의 앞뒤 공백 제거:${fn:trim(str1)}<br>		<%--앞뒤 공백 제거--%>
문자열에서 원래 문자를 지정한 문자로 대체:${fn:replace(str1," ","-")}<br> <%--공백을 하이픈으로 대치--%>
</body>
</html>