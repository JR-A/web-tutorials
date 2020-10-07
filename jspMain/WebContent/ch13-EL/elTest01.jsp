<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL(Expression Language) 예제 1</title>
</head>
<body>
<table border="1">
	<tr>
		<th>표현식</th>
		<th>값</th>
	</tr>
	<tr>
		<td>\${2 + 5}</td>
		<td>${2 + 5}</td>
	</tr>
	
	<%-- EL에서는 문자열에서 + 하여도 문자열'연결'이 아닌 '연산'수행 --%>
	<tr>
		<td>\${"10" + 5}</td>
		<td>${"10" + 5}</td>
	</tr>
	<tr>
		<td>\${"10" + "5"}</td>
		<td>${"10" + "5"}</td>
	</tr>
	<tr>
		<td>\${"십" + "5"}</td>
		<td>에러발생(EL에서의 +는 연산만 가능)</td>
	</tr>
	
	<%-- EL에서 '정수/정수'의 결과로 '실수' 나올 수 있음 유의. 자바스크립트와 유사(자바는 '정수/정수'결과 정수만 나옴) --%>
	<tr>
		<td>\${4/5}</td>
		<td>${4/5}</td>
	</tr>
	
	<%-- 0으로 나누면 예외발생되지 않고 Infinity로 출력. 자바스크립트와 유사 --%>
	<tr>
		<td>\${5/0}</td>
		<td>${5/0}</td>
	</tr>
</table>
</body>
</html>