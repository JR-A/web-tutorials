<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//회원 정보 수정 유효성 체크
		$('#modify_user_form').submit(function(){
			if($('#name').val()==''){
				alert('이름을 입력하세요!');
				$('#name').focus();
				return false;  //submit되지 않도록 return false
			}
			if($('#phone').val()==''){
				alert('전화번호를 입력하세요!');
				$('#phone').focus();
				return false;
			}
			if($('#email').val()==''){
				alert('메일을 입력하세요!');
				$('#email').focus();
				return false;
			}
			if($('#zipcode').val()==''){
				alert('우편번호를 입력하세요!');
				$('#zipcode').focus();
				return false;
			}
			if($('#address1').val()==''){
				alert('주소를 입력하세요!');
				$('#address1').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main-style">
	<h2>회원 정보 수정</h2>
	<form id="modify_user_form" action="modifyUser.do" method="post">
		<ul>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="${member.name}" maxlength="10" autocomplete="off">
			</li>
			<li>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" value="${member.phone}" maxlength="15">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" value="${member.email}" maxlength="50">
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<input type="text" name="zipcode" id="zipcode" value="${member.zipcode}" size="5" maxlength="5">
			</li>
			<li>
				<label for="address1">주소</label>
				<input type="text" name="address1" id="address1" value="${member.address1}" maxlength="30">
			</li>
			<li>
				<label for="address2">나머지주소</label>
				<input type="text" name="address2" id="address2" value="${member.address2}" maxlength="30">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
		<input type="button" value="MyPage" onclick="location.href='myPage.do'">
		</div>
	</form>
</div>