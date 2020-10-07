<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 예제 2</title>
</head>
<body>
<form action="elTest02.jsp" method="post"> <%-- 자기 자신을 호출 --%>
	이름 <input type="text" name="name"><br>
	<input type="submit" value="전송">
</form>
<br>
<%-- post방식으로 전달받은 request이용하여 출력 --%>
이름은 <%= request.getParameter("name") %><br> <%-- 전송된 데이터가 없으면 null로 표시 --%>

<%-- EL의 내장객체 param 이용하여 출력 --%>
이름은 ${param.name}	<%-- null이 아닌 빈 문자열로 처리. EL의 특성 --%>

</body>
</html>