<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>out 태그 </title>
</head>
<body>
<h4 style="color:green">escapeXml="true"이면 HTML 태그로 인정하지 않는다는 의미(기본값이 true)</h4>
<c:out value="<h1>오늘은 좋은 날!</h1>" escapeXml="true"/>
<h4 style="color:green">escapeXml="false"이면 HTML 태그로 인정</h4>
<c:out value="<h1>오늘은 좋은 날2!</h1>" escapeXml="false"/>
</body>
</html>