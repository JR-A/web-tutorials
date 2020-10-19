<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL의 core라이브러리 사용하기 위해 링크 추가 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//이벤트 연결
		$('#update_form').submit(function(){
			//글 등록 유효성 체크
			if($('#title').val()==''){
				alert('제목을 입력하세요!');
				$('#title').focus();
				return false;
			}
			if($('#content').val()==''){
				alert('내용을 입력하세요!');
				$('#content').focus();
				return false;
			}
			//file은 업로드 안해도 무방
		});
	});
</script>
<div class="page-main-style">
	<h2>게시판 글 수정</h2>									<%-- 파일전송 위해 인코딩타입 지정(파일이나 이미지를 서버로 전송시) --%>
	<form id="update_form" action="update.do" method="post" enctype="multipart/form-data">
		<%--board_num식별 위해 hidden속성으로 전송--%>
		<input type="hidden" name="board_num" value="${board.board_num}">
		
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="${board.title}" maxlength="50">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="5" cols="30" name="content" id="content">${board.content}</textarea>
			</li>
			<li>
				<label for="filename">이미지 파일</label>	  <%-- 이미지 파일 검색 허용. 유효성검사로는 사용불가 --%>
				<input type="file" name="filename" id="filename" accept="image/*">
				<c:if test="${!empty board.filename}">
				<br>
				<span>(${board.filename})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</div>