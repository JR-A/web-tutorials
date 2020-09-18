<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 연습</title>
</head>
<body>

<!-- 방법 1 ) 스크립트만으로 출력 (서블릿처럼 쓰게됨) -->
<h2>배열의 내용 출력 - 선언부, 스크립트릿</h2>
<%!
	//선언부 : 변수 선언, 메서드 선언 => 서블릿 클래스의 멤버변수,멤버메서드로 선언됨 -> 권장되지않음. 스크립트로 거의 모두 표현
	String[] str = {"JSP가", "정말","재미","있다"};
%>
<table border="1">
	<tr>
		<th>배열의 인덱스</th>
		<th>배열의 내용</th>
	</tr>
<%
	//스크립트릿 : 변수 선언, 연산, 제어문, 출력	=> 서블릿 클래스의 doGet, doPost 메서드의 지역변수로 선언됨
	for(int i=0; i<str.length; i++){
		//out은 jsp의 내장객체! jsp페이지가 생성하는 결과 출력할 때 사용되는 출력 스트림
		out.println("<tr>");			
		out.println("	<td>" + i + "</td>");
		out.println("	<td>" + str[i] + "</td>");
		out.println("</tr>");
	}
%>
</table>
<br>
<!-- ================================================= -->
<!-- 방법 2 ) 스크립트릿+표현식을 사용하는 방법(권장)-->    <!-- 방법 1과 페이지 소스는 같게 나옴 -->
<h2>배열의 내용 출력 - 선언부, 스크립트릿, 표현식</h2>
<table border="1">
	<tr>
		<th>배열의 인덱스</th>
		<th>배열의 내용</th>
	</tr>
	
	<!-- 스크립트릿 안에 표현식 넣을 수 없음. 영역을 분리시켜야 함 -->
<% 
	for(int i=0; i<str.length; i++){ 
%>
	<tr> 
		<!-- 표현식 : 변수의 값 출력, 메서드의 결과값 출력, 연산의 결과 출력 --> <!-- 주석을 for문 안에 명시하면 주석도 반복되어 들어감(소스상에서 다 보인다) jsp주석 사용하면 소스상에서 숨김처리 -->
		<%-- JSP 주석 : 소스 보기할 때 숨겨짐 --%>
		<td><%= i %></td>
		<td><%= str[i] %></td>
	</tr>
<%	
	} 
%>
</table>
<br>
<!-- ================================================= -->
<!-- 방법 3 ) 확장 for문 사용  -->
<h2>배열의 내용 출력 - 확장 for문 사용</h2>
<table border="1">
	<tr>
		<th>배열의 내용</th>
	</tr>
<% 
	for (String s : str){
%>
	<tr>
		<td><%= s %></td>	
	</tr>
<%		
	}
%>
</table>
</body>
</html>