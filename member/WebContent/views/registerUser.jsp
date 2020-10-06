<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	//자바빈 생성
	MemberVO member = new MemberVO();
	member.setId(request.getParameter("id"));
	member.setName(request.getParameter("name"));
	member.setPasswd(request.getParameter("passwd"));
	member.setEmail(request.getParameter("email"));
	member.setPhone(request.getParameter("phone"));
	
	//DAO호출
	MemberDAO dao = MemberDAO.getInstance();
	//전송된 데이터가 저장된 VO를 DAO의 insertMember 메서드에 전달
	dao.insertMember(member);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>

</head>
<body>
<div class="page-main-style">
	<h1>회원가입 완료!!</h1>
	<div class="result-display">
		회원가입 성공!
		<button onclick="location.href='main.jsp'">홈으로</button>
	</div>
</div>
</body>
</html>