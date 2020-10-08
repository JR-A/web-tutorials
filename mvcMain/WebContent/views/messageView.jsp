<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View(뷰)</title>
</head>
<body>
<%-- 1.표현식 2.EL 이용하여 값 읽어올 수 있다 --%>
결과 : <%= request.getAttribute("result") %><br>
결과 : ${result}
</body>
</html> 