<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%-- text/plain : 데이터만 전송하므로 html이 아닌 텍스트로 만듦 --%>
    <%-- trimDirectiveWhitespaces="true" : 줄바꿈시 생기는 공백문자 무시하기 --%> 
    
    <%-- s02.html 에서 실행 --%>
 
 <%
 	String name = request.getParameter("name");
 	String age = request.getParameter("age");
 %>
 이름은 <%= name %>이고 나이는 <%= age %>살 입니다.