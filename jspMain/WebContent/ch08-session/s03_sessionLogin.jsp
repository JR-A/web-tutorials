<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%-- s02_sessionLoginForm.jsp 에서 실행 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 체크</title>
</head>
<body>
<%-- id와 비밀번호가 일치하면 로그인 성공으로 간주 --%>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	//id와 비밀번호가 일치하는 경우
	if(id.equals(password)){ //로그인 성공
							//속성명	 속성값
		session.setAttribute("userId", id);
%>
		<%= id %>님이 로그인했습니다.<br>
		<input type="button" value="로그인 체크" onclick="location.href='s04_sessionLoginCheck.jsp'">
		<input type="button" value="로그아웃" onclick="location.href='s05_sessionLogoutCheck.jsp'"> 
<%
	} else{ //로그인 실패
%>
		<script type="text/javascript">
			alert("로그인에 실패했습니다.");
			history.go(-1);
		</script>
<%
	}
%>
</body>
</html>