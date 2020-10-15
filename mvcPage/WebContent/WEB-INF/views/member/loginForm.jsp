<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script> <%-- jQuery 사용 위해 링크 추가 --%>
<script type="text/javascript">
	$(document).ready(function(){
		//이벤트 연결
		$('#login_form').submit(function(){
			//유효성 체크
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return false;
			}
			if($('#passwd').val()==''){
				alert('비밀번호를 입려하세요!');
				$('#passwd').focus();
				return false;
			}
		});
	});
</script>    
<div class="page-main-style">
	<h2>로그인</h2>
	<form id="login_form" action="login.do" method="post">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="12">
			</li>
			<li>
				<label for="passwd">패스워드</label>
				<input type="password" name="passwd" id="passwd" maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form>
</div>