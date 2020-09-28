<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%-- text/xml 로 변경 --%>
	
	<%-- s03.html 에서 실행 --%>
	
<?xml version="1.0" encoding="UTF-8"?>
<people>
	<person>	<%-- people의 하위태그. 하나의 레코드 --%>
		<name>강호동</name>	<%-- name, job, age는 key(식별자)임 --%>
		<job>개그맨</job>
		<age>40</age>
	</person>
	<person>
		<name>아이유</name>
		<job>가수</job>
		<age>28</age>
	</person>
</people>

<%-- 실행해보면 xml데이터를 트리형식으로 보여줌. 에러있으면 에러 표시 --%>