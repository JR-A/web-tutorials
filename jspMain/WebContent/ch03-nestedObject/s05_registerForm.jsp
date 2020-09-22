<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[실습] 회원 가입</title>
</head>
<body>
<!-- 
	이름(name), 아이디(id), 비밀번호(password), 전화번호(phone), 
	자기소개(content, textarea)
	s06_register.jsp로 전송. 전송 방법 post
	s06_register.jsp에서 전송된 데이터 출력
	이름 : 홍길동
	ID : dragon
	비밀번호 : 1234
	전화번호 : 010-1234-5678
	자기소개 : 서울에서 태어나서 계속 서울에 거주
 -->
<form action="s06_register.jsp" method="post">
이름	:	<input type="text" name="name" size="10"><br>
ID	:	<input type="text" name="id" size="10"><br>
비밀번호	:	<input type="password" name="password" size="10"><br>
전화번호	:	<input type="tel" name="phone" size="30" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"><br>
자기소개	:<br>
<textarea rows="5" cols="30" name="content"></textarea>
<br>
<input type="submit" value="전송">
</form>

</body>
</html>