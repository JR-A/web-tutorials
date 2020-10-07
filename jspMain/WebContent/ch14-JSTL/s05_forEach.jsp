<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach 태그(for문, 확장for문)</title>
</head>
<body>

<h4>1부터 100까지 홀수의 합</h4>
<c:set var="sum" value="0"/>

<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum + i}" /> <%--누적--%>
</c:forEach>
결과 = ${sum}
<br>----------------------------
<h4>구구단  : 5단</h4>
<ul>
	<%-- 1씩 증가하는 경우 step="1" 생략 가능 --%>
	<c:forEach var="i" begin="1" end="9">
		<li>5 * ${i} = ${5 * i}</li>
	</c:forEach>
</ul>
----------------------------
<h4>int형 배열</h4>
<c:set var="intArray" value="<%= new int[]{10, 20, 30, 40, 50} %>"/>

<h5>items : 속성에 인덱스가 존재하는 객체를 지정하여 반복수행할 때 사용. 확장 for문 역할</h5>
<c:forEach var="i" items="${intArray}">
	${i} 
</c:forEach>

<h5>begin, end속성 이용해 배열(인덱스 속성 가진 객체)의 특정 범위만 출력 가능</h5>
<c:forEach var="i" items="${intArray}" begin="2" end="4">
	${i} 
</c:forEach>

<h5>varStatus : 반복 상태를 지정하는 변수 지정</h5>
<c:forEach var="i" items="${intArray}" begin="2" end="4" varStatus="status">
	<%-- 인덱스				반복횟수		 값 --%>
	${status.index} - ${status.count} - ${i}<br>
</c:forEach>
----------------------------
<h4>Map</h4>
<%
	HashMap<String, String> mapData = new HashMap<String, String>();
	mapData.put("name", "홍길동");
	mapData.put("job", "경찰");
	mapData.put("hobby", "음악감상");
%>
<c:set var="map" value="<%= mapData %>"/>
<c:forEach var="i" items="${map}">
	${i.key} = ${i.value}<br>
</c:forEach>

<h4></h4>
</body>
</html>