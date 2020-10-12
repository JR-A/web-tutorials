<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- index.jsp는 직접 호출되어야하므로 WebContent에 추가 --%>
<%-- 대문페이지 --%>
<%
	response.sendRedirect(request.getContextPath()+"/list.do");
%>