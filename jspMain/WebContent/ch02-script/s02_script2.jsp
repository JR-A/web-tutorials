<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//선언부 : 변수 선언, 메서드 선언 - 다른 페이지에서는 메서드 호출 불가. 재활용성 떨어짐. 메서드가 사실상 거의 의미없음
	public int multiply(int a, int b){
		return a*b;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언부를 사용한 두 정수값의 곱</title>
</head>
<body>
10 * 25 = <%= multiply(10,25) %>
</body>
</html>