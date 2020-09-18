<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[실습] 성적처리</title>
</head>
<body>
<%-- 
	[문제] 성적 관리 배열(score) 생성 - 국어, 수학, 영어
		 스크립트릿과 표현식만 사용
	출력예시)
	국어 : 88
	수학 : 99
	영어 : 86
	총점 : 273
	평균 : 91 (정수)
--%>

<%
	int[] score = { 88, 99, 86 };
	String[] subjects = {"국어", "수학", "영어"};
	int sum = 0;
	int avg = 0;
	
	for(int i=0; i<score.length; i++){
		sum += score[i];
%>
		<%= subjects[i] %> : <%= score[i] %><br>
<%
	}
	avg = sum/score.length;
%>
총점 : <%= sum %><br>
평균 : <%= avg %>

</body>
</html>