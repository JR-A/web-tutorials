<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	String user_id = (String)session.getAttribute("user_id");
	if(user_id == null){ 	//로그인되지 않은 상태
		response.sendRedirect("loginForm.jsp");
	}else{	//로그인 된 상태
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//id와 비밀번호 일치 여부 체크(회원이 존재하는지 체크)
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		//아이디가 등록되어 있고 로그인한 아이디와 입력한 아이디가 일치하는 경우
		if(member != null && user_id.equals(id)){
			//비밀번호 일치 여부 체크
			check = member.isCheckedPasswd(passwd);
		}
		
		if(check){ //인증 성공
			//회원 정보 삭제
			dao.deleteMember(member.getNum());
			//로그아웃
			session.invalidate(); //세션 무효화
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>
</head>
<body>
<div class="page-main-style">
	<h1>회원 탈퇴 완료</h1>
	<div class="result-display">
		회원 탈퇴가 완료되었습니다.
		<button onclick="location.href='main.jsp'">홈으로</button>
	</div>
</div>
</body>
</html>
					
<%			
		}else{ //인증 실패
%>
		<script>
			alert('아이디 또는 비밀번호가 불일치합니다.');
			history.go(-1);	//이전 페이지로 이동
		</script>
<%
		}
	}
%>

