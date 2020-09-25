<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<link rel="stylesheet" href="style.css" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		var myForm = document.getElementById('myForm');
		//이벤트 연결
		myForm.onsubmit=function(){
			var name = document.getElementById('name');
			if(name.value==''){
				alert('이름을 입력하세요!');
				name.focus();
				return false;
			}
			var title = document.getElementById('title');
			if(title.value==''){
				alert('제목을 입력하세요!');
				title.focus();
				return false;
			}
			var passwd = document.getElementById('passwd');
			if(passwd.value==''){
				alert('비밀번호를 입력하세요!');
				passwd.focus();
				return false;
			}
			var content = document.getElementById('content');
			if(content.value==''){
				alert('내용을 입력하세요!');
				content.focus();
				return false;
			}
		};
	};
</script>
</head>
<body>
<div class="page-main-style">
	<h2>글쓰기</h2>
	<form id="myForm" action="insertTest.jsp" method="post"> <%-- 전송버튼 클릭시 insertTest.jsp 실행 --%>
		<ul>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" size="20" maxLength="10">
			</li>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" size="30" maxLength="50">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="20" maxLength="10">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="5" cols="40" name="content" id="content"></textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</form>
</div>
</body>
</html>