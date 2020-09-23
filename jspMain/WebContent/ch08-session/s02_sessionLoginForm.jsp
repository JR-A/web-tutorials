<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인폼</title>
</head>
<body>
<%-- id와 비밀번호가 일치하면 로그인 성공으로 간주 --%>
<form action="s03_sessionLogin.jsp" method="post">
	아이디 <input type="text" name="id" size="10">
	비밀번호 <input type="password" name="password" size="10">
	<input type="submit" value="로그인">
</form>
</body>
</html>