<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catch 태그(예외 발생시 예외 처리 태그)</title>
</head>
<body>

<c:catch var="ex">
	<%--예외가 발생할 가능성이 있는 태그 --%>
	name 파라미터의 값 = <%= request.getParameter("name") %><br> 
	<%
		if(request.getParameter("name").equals("test")){	//파라미터 넘겨주지 않으면 null. null인채로 equals메소드 접근하면 NullPointerException
	%>
			${param.name}은 test입니다.
	<%
		}
	%>
</c:catch>
<p>
<c:if test="${ex != null}">
	예외가 발생했습니다.<br>
	${ex}	<%-- 예외객체의 문구를 출력하는 것은 보안상 좋지 않음 --%>
</c:if>
</p>
</body>
</html>