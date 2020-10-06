<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%-- text/plain : 데이터만 전송하므로 html이 아닌 텍스트로 만듦 --%>
    <%-- trimDirectiveWhitespaces="true" : 줄바꿈시 생기는 공백문자 무시하기 --%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>

<%-- confirmId.jsp?id=s get방식 이용해 확인 --%>

<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
	
	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO member = dao.checkMember(id);
	if(member != null){ //아이디 등록
%>
	<%-- json --%>
		{
			"result":"success",
			"id":"idDuplicated"			
		}
<%
	}else{ //아이디 미등록
%>
	<%-- json --%>
		{
			"result":"success",
			"id":"idNotFound"			
		}
<%		
	}
%>