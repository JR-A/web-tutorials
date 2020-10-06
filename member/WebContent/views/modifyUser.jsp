<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num == null) { //로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{ //로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈 객체 생성하여 전송된 데이터를 자바빈에 저장
		MemberVO member = new MemberVO();
		member.setNum(user_num);
		member.setName(request.getParameter("name"));
		member.setPasswd(request.getParameter("passwd"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		
		//MemberDAO 호출
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>
</head>
<body>
<div class="page-main-style">
	<h1>회원 정보 수정 완료</h1>
	<div class="result-display">
		회원 정보 수정 완료!
		<button onclick="location.href='main.jsp'">홈으로</button>
	</div>
</div>
</body>
</html>