<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css"> <%-- 경로 명시할 때 context경로부터 시작하면 파일명 바뀌어도 경로 찾아갈 수 있음 --%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//아이디 중복체크 플래그
		var count = 0;
		
		//ID중복확인 버튼 이벤트 연결
		$('#confirm_id').click(function(){
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return;	//함수 빠져나가기 위해 return;
			}
			
			//서버프로그램과 통신(AJAX방식)
			$.ajax({
				url: 'confirmId.jsp',
				type: 'post',
				data: {id:$('#id').val()},
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success:function(param){	<%--서버 통신 정상작동시--%>
					if(param.result == 'success'){
						<%--아이디 중복체크--%>
						if(param.id == 'idDuplicated'){
							count = 0;
							$('#id_signed').text('아이디 중복').css('color', 'red');
							$('#id').val('').focus();
							
						}else if(param.id == 'idNotFound'){
							count = 1;
							$('#id_signed').text('사용 가능 아이디').css('color', 'green');
							
						}else{		<%--id가 idDuplicated도 아니고, idNotFound도 아닌 경우--%>
							count = 0;
							alert('오류 발생!');
							
						}
						
					}else{			<%--result가 success가 아닌 경우--%>
						count = 0;
						alert('오류 발생!!');
					}
				},
				error:function(){			<%--서버 통신 에러발생시--%>
					count = 0;
					alert('네트워크 오류 발생!');
				}
			});
			
		});
		
		
		//id 입력박스의 값을 변경할 때 이벤트 연결 (중복체크 후 임의로 아이디 변경하는 경우 방지)
		$('#register_form #id').keydown(function(){
			count = 0;
			$('#id_signed').text('');
		});
		
		
		//form에서 발생하는 이벤트 연결
		$('#register_form').submit(function(){
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return false;	//submit하지 않기 위해 false리턴
			}
			
			if(count == 0){ //아이디 중복체크 완료되지 않은 경우
				alert('아이디 중복체크 필수');
				return false;
			}
			
			if($('#name').val()==''){
				alert('이름을 입력하세요!');
				$('#name').focus();
				return false;
			}
			if($('#passwd').val()==''){
				alert('비밀번호를 입력하세요!');
				$('#passwd').focus();	
				return false;
			}
			if($('#email').val()==''){
				alert('이메일을 입력하세요!');
				$('#email').focus();
				return false;
			}
		});
		
	});
</script>
</head>
<body>
<div class="page-main-style">
	<h1>회원 가입</h1>
	<form action="registerUser.jsp" method="post" id="register_form">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" size="7" maxlength="12" autocomplete="off">
				<input type="button" id="confirm_id" value="ID중복확인">
				<span id="id_signed"></span>
			</li>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" maxlength="10">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="12">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" maxlength="50">
			</li>
			<li>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" maxlength="15">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>