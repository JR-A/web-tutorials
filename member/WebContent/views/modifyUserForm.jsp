<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num == null){	//로그인 되지 않은 상태
		response.sendRedirect("loginForm.jsp");
	} else{ //로그인 된 상태
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>
<script type="text/javascript">
	window.onload=function(){
		var form = document.getElementById('modify_form');
		
		//이벤트 연결
		form.onsubmit = function(){  //function은 이벤트 핸들러
			var name = document.getElementById('name');
			if(name.value == ''){
				alert('이름을 입력하세요!');
				name.focus();
				return false;
			}
			var passwd = document.getElementById('passwd');
			if(passwd.value == ''){
				alert('비밀번호를 입력하세요!');
				passwd.focus();
				return false;
			}
			var email = document.getElementById('email');
			if(email.value == ''){
				alert('이메일을 입력하세요!');
				email.focus();
				return false;
			}
		};
	};
</script>
</head>
<body>
<%
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO member = dao.getMember(user_num);
	
	//phone의 경우는 입력값이 없을 때 null로 출력되기때문에 이를 방지해야함
	if(member.getPhone() == null){
		member.setPhone("");
	}
%>
<div class="page-main-style">
	<h1>회원 정보 수정</h1>
	<form action="modifyUser.jsp" method="post" id="modify_form">
		<%-- 회원 가입 폼과 유사하나 현재 로그인된 멤버 정보를 보여주어 수정 가능하도록 함 --%>
		<ul>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%= member.getName() %>" maxlength="10">
			</li>
			<li>
				<%-- 비밀번호 변경 기능은 분리하는 것이 바람직 --%>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="12">	
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" value="<%= member.getEmail() %>" maxlength="50">
			</li>
			<li>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" value="<%= member.getPhone() %>" maxlength="15">
			</li>
		</ul>
		<div class="align-center"> 
			<input type="submit" value="수정">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>

<%
	}
%>