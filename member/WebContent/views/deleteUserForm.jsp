<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num == null){ //로그인이 되지 않은 상태
		response.sendRedirect("loginForm.jsp");
	}else{ //로그인 된 상태
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 폼</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>
<script type="text/javascript">
	window.onload=function(){
		var form = document.getElementById('delete_from');
		
		//이벤트 연결
		form.onsubmit=function(){
			//공란 확인
			var id = document.getElementById('id');
			if(id.value==''){
				alert('아이디를 입력하세요!');
				id.focus();
				return false;
			}
			var passwd = document.getElementById('passwd');
			if(passwd.value==''){
				alert('비밀번호를 입력하세요!');
				passwd.focus();
				return false;
			}
			var cpasswd = document.getElementById('cpasswd');
			if(cpasswd.value==''){
				alert('비밀번호 확인을 입력하세요!');
				cpasswd.focus();
				return false;
			}
			
			//비밀번호와 비밀번호확인란이 일치하는지 확인
			if(passwd.value != cpasswd.value){
				alert('비밀번호와 비밀번호 확인이 불일치합니다!');
				cpasswd.vlaue = '';
				cpasswd.focus();                                                 
				return false;
			}
			
		};
	};
</script>
</head>
<body>
<div class="page-name-style">
	<h1>회원 탈퇴</h1>
	<form id="delete_from" action="deleteUser.jsp" method="post">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="12">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="12">
			</li>
			<li>
				<label for="cpasswd">비밀번호 확인</label>
				<input type="password" name="cpasswd" id="cpasswd" maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="회원탈퇴">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>

<%
	}
%>