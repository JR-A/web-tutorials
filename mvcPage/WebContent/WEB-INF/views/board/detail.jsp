<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL의 core라이브러리 사용하기 위해 링크 추가 --%>
<script type="text/javascript">
	window.onload=function(){
		var delete_btn = document.getElementById("delete_btn");
		
		//클릭 이벤트 연결
		delete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			
			if(choice){
				location.replace('delete.do?board_num=${board.board_num}');	//history남지 않음(back불가능)
			}
		};
	};
</script>
<div class="page-main-style">
	<h2>게시판 글 상세</h2>
	<ul>		<%-- .key로 value접근 --%>
		<li>글번호 : ${board.board_num}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.id}</li>
		<li>조회수 : ${board.hit}</li>
	</ul>
	<hr size="1" width="100%" noshade="noshade"> <%--구분선. noshade입체감 없도록 --%>
	<div class="align-center">
	<c:if test="${!empty board.filename}"> <%-- filename이 존재하면 파일 링크 걸어 이미지 표시 --%>
		<img class="detail-img" src="${pageContext.request.contextPath}/upload/${board.filename}">
	</c:if>
	</div>
	<p>
		${board.content}	<%-- 내용 표시 --%>
	</p>
	<div class="align-right">
		작성일 : ${board.reg_date}
		최종 수정일 : ${board.modify_date}<br>
		<c:if test="${user_num == board.mem_num}"> <%-- 로그인된 회원번호와 작성자 회원번호가 일치하면 수정, 삭제 버튼 표시 --%>
			<input type="button" value="수정" onclick="location.href='updateForm.do?board_num=${board.board_num}'"> <%-- get방식으로 글 번호 넘겨줌 --%>
			<input type="button" value="삭제" id="delete_btn">
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>