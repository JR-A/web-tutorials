<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	//전송된 데이터 반환
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	//DAO호출
	MemberDAO dao = MemberDAO.getInstance();
	//id존재 여부 확인
	MemberVO member = dao.checkMember(id);
	boolean check = false;
	
	if(member!=null){	//객체가 null이 아니다 == id 존재
		//사용자가 입력한 비밀번호와  DB테이블에 저장된 비밀번호의 일치여부 확인
			   					//사용자가 입력한 비밀번호
		check = member.isCheckedPasswd(passwd);
	}
	
	if(check){ //인증 성공
		//로그인 처리
		session.setAttribute("user_id", id);	//세션에 아이디 저장
		session.setAttribute("user_num", member.getNum()); //세션에 회원 번호 저장
		//서버 세션이므로 크롬브라우저에서 확인 불가
		
		//redirect(리다이렉트)
		response.sendRedirect("main.jsp");
		
	}else{	//인증 실패(아이디 또는 비밀번호가 불일치)
%>
		<script type="text/javascript">
			alert('아이디 또는 비밀번호가 불일치합니다.'); //보안상 이유로 어느것이 불일치하는지 명시하지 않음
			history.go(-1);		//전 페이지로 이동(loginForm.jsp)
		</script>
<%
	}
%>