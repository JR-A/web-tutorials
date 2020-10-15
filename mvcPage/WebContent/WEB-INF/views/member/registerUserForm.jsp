<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//아이디 중복체크 여부 플래그
		var idChecked = 0;
		
		//아이디 중복 체크 버튼 클릭이벤트연결
		$('#id_check').click(function(){
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return;	//클릭 이벤트 함수 종료 위해 return
			}
			
			$('#message_id').text('');	//중복체크여부 메시지 초기화
			$('#loading').show();		//로딩이미지 노출
			
			//서버와 ajax비동기 통신
			$.ajax({
				url: 'checkId.do',		//요청URL
				type: 'post',			//전송방식
				data: {id: $('#id').val()},	//서버에 전송할 데이터
				dataType: 'json',			//서버가 전송하는 데이터의 형식
				cache: false,				//예전 데이터 저장해 사용하지 않도록 제한
				timeout: 30000,				//ms(1/1000초) 3초 지나도 정보 도착하지 않으면 포기
				success: function(param){	//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수
											//param: ajax메소드가 서버로부터 전송받은 데이터(param는 매개변수명이므로 변경가능)
					$('#loading').hide();	//로딩 이미지 감추기
						//.key로 value 접근 
					if(param.result == 'idNotFound'){	//아이디 미중복
						$('#message_id').css('color', '#000000').text('등록 가능 ID');
						idChecked = 1;
					}else if(param.result == 'idDuplicated'){	//아이디 중복
						$('#message_id').css('color', 'red').text('중복된 ID');
						$('#id').val('').focus();
						idChecked = 0;
					}else{	//result값이 idNotFound도 아니고 idDuplicated도 아닌경우(에러)
						alert('아이디 중복 체크 오류 발생!');
						idChecked = 0;
					}
				},
				error: function(){			//서버에 데이터를 요청했으나 실패한 경우 호출되는 함수
					$('#loading').hide();	//로딩 이미지 감추기
					alert('네트워크 오류 발생');
					idChecked = 0;
				}
			});
		});
		
		//(아이디 중복체크 한 후)아이디 입력창에 키 입력되는 경우
		$('#register_form #id').keydown(function(){
			idChecked = 0;	//아이디 중복값 초기화
			$('#message_id').text('');	//중복체크여부 메시지 초기화
		});
		
		//회원 정보 등록 유효성 체크
		$('#register_form').submit(function(){
			if($('#id').val()==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return false; //submit되지 않도록 return false
			}
			
			//아이디 중복체크되지 않은 경우
			if(idChecked == 0){
				alert('아이디 중복 체크 필수!');
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
	<h2>회원 가입</h2>
	<form id="register_form" action="registerUser.do" method="post">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" size="6" maxlength="12" autocomplete="off">
				<input type="button" value="ID중복체크" id="id_check"> <%-- 클릭이벤트 jQuery로 작성 --%>
				<span id="message_id"></span>	<%-- 중복체크여부 메시지 --%>
				<%-- 로딩 이미지 --%>
				<img src="${pageContext.request.contextPath}/images/ajax-loader.gif" width="16" height="16" id="loading" style="display:none;">
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
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" maxlength="15">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" maxlength="50">
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<input type="text" name="zipcode" id="zipcode" size="5" maxlength="5">
			</li>
			<li>
				<label for="address1">주소</label>
				<input type="text" name="address1" id="address1" maxlength="30">
			</li>
			<li>
				<label for="address2">나머지주소</label>
				<input type="text" name="address2" id="address2" maxlength="30">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form>
</div>