<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){		
		//비밀번호 수정 유효성 체크
		$('#modify_password_form').submit(function(){
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return false;
			}
			if($('#origin_passwd').val()==''){
				alert('현재 비밀번호를 입력하세요!');
				$('#origin_passwd').focus();
				return false;
			}
			if($('#passwd').val()==''){
				alert('변경 비밀번호를 입력하세요!');
				$('#passwd').focus();
				return false;
			}
			if($('#cpasswd').val()==''){
				alert('변경 비밀번호 확인을 입력하세요!');
				$('#cpasswd').focus();
				return false;
			}
			//변경 비밀번호 일치 체크
			if($('#passwd').val() != $('#cpasswd').val()){
				alert('변경 비밀번호와 변경 비밀번호 확인이 일치하지 않습니다!');
				$('#cpasswd').val('').focus();
				return false;
			}
		});
		
		//변경 비밀번호 확인 후 다시 비밀번호를 수정하는 경우 변경 비밀번호 확인과 메시지를 초기화
		$('#modify_password_form #passwd').keyup(function(){
			$('#cpasswd').val('');
			$('#message_cpasswd').text('');
		});
		
		//변경 비밀번호 확인을 입력할 때 변경 비밀번호와 일치하는지 체크
		$('#modify_password_form #cpasswd').keyup(function(){
			if($('#passwd').val() == $('#cpasswd').val()){
				$('#message_cpasswd').text('비밀번호 일치');
			}else{
				$('#message_cpasswd').text('');
			}
		});
		
	});
</script>
<div class="page-main-style">
	<h2>비밀번호 수정</h2>
	<form id="modify_password_form" action="modifyPassword.do" method="post">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" maxlength="12">
			</li>
			<li>
				<label for="origin_passwd">현재 비밀번호</label>
				<input type="password" id="origin_passwd" name="origin_passwd" maxlength="12">
			</li>
			<li>
				<label for="passwd">변경 비밀번호</label>
				<input type="password" id="passwd" name="passwd" maxlength="12">
			</li>
			<li>
				<label for="cpasswd">변경 비밀번호 확인</label>
				<input type="password" id="cpasswd" name="cpasswd" maxlength="12">
				<span id="message_cpasswd"></span>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="비밀번호 수정">
			<input type="button" value="MyPage" onclick="location.href='myPage.do'">
		</div>
	</form>
</div>