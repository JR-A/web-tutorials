<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.Member" %> <%-- 자바빈 import --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--jsp페이지에 jstl의 core 라이브러리를 사용할 수 있도록 taglib 디렉티브 명시--%>
<%-- prefix:접두사, uri:core라이브러라가 존재하는 위치 --%>
<%--
	JSTL(JavaServer Pages Standard Tag Library)
	: Java EE 기반의 웹 애플리케이션 개발 플랫폼을 위한 컴포넌트 모음
	: JSP 페이지 내에서 자바 코드를 바로 사용하지 않고 로직을 내장하는 효율적인 방법을 제공
	커스텀태그(사용자 정의 태그) 사용
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>set 태그(변수명에 값을 할당)</title>
</head>
<body>
<%-- scope을 생략하면 기본적으로 page영역에 저장 --%>
<%-- 		속성명		속성값	  데이터저장영역 --%>
<c:set var="msg" value="봄 " scope="page"/>
${pageScope.msg}, ${msg}<br>	<%-- 속성명이 unique할 경우 영역 생략하고 속성명만 명시하여도 호출 가능 --%>
<%
	Member member = new Member();
%>
<%-- Member 객체를 page 영역에 저장 --%>
<%--		속성명			       속성값 --%>
<c:set var="member" value="<%= member %>" />
<%-- (EL이읽은)페이지영역에 저장된 객체를 참조	 멤버변수		저장할 값 --%>
<c:set target="${member}" property="name" value="홍길동" />
방법1 - 회원 이름 : ${member.name}<br>	<%-- 자바문법으로는 private인 객체의 속성에 접근할 수 없지만, EL에서는 '.속성명'으로 호출하는것이 표준 (권장) --%>
방법2 - 회원 이름 : ${member.getName()}<br>	<%-- 원칙상 방법1로 해야하지만 메소드로 호출하는 것도 허용. 하위버전과의 호환성 위해 방법1 권장 --%>
</body>
</html>