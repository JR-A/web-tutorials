<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그아웃
	session.invalidate();	//세션 무효화. 세션 객체를 메모리에서 삭제하지는 않는다
	//redirect(리다이렉트)
	response.sendRedirect("main.jsp");
%>