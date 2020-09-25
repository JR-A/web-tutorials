<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %> <%-- db연동시 필요한 자원들 --%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>

<%@ include file="dbInfo.jspf" %> <%-- jsp파일에 dbInfo.jspf를 삽입해서 변수 공유. 반복적으로 쓰이는 db정보 --%>

	<%-- selectTest.jsp에서 실행하여 글 목록 불러오고, 특정 게시물 선택해서 detailTest.jsp 실행 후 수정 버튼 클릭 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정</title>
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
<%
	//전송된 글 번호 반환
	int num = Integer.parseInt(request.getParameter("num")); //detailTest.jsp로부터 전달받음(get방식이므로 인코딩불필요)
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(driverName);
	
		//JDBC 수행 2단계 : Connection 객체 생성(db ID와 비밀번호로 인증)
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
	
		//SQL문 작성
		sql = "SELECT * FROM tboard WHERE num=?";
		
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setInt(1, num); //num은 detailTest.jsp에서 전달받음
		
		//JDBC 수행 4단계 : SQL문 실행, 테이블에 반영하고 결과행을 ResultSet에 담음
		rs = pstmt.executeQuery();
		if(rs.next()){	//하나의 레코드 반환하므로 if문
			int tnum = rs.getInt("num");
			String name = rs.getString("name");
			String title = rs.getString("title");
			String content = rs.getString("content");

%>
<div class="page-main-style">
	<h2>글 수정</h2>
	<form id="myForm" action="updateTest.jsp" method="post">	<%-- 전송 버튼 클릭시 실행 --%>
		<%-- 사용자로부터 글 번호 숨김(수정가능성 차단). 정보 취합해 db에 반영해야하므로 글 번호 필요 --%>
		<input type="hidden" name="num" value="<%= tnum %>">
		<ul>
			
			<li>
				<label for="name">이름</label>			<%-- db에서 불러온 정보 표시 --%>
				<input type="text" name="name" id="name" value="<%= name %>" size="20" maxLength="10">
			</li>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="<%= title %>" size="30" maxLength="50">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="20" maxLength="10">
			</li>
			<li>
				<label for="content">내용</label>				<%-- 시작태그,끝태그 사이에 내용 표시. 공백 유의 --%>
				<textarea rows="5" cols="40" name="content" id="content"><%= content %></textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</form>
</div>
<%
		}else{
%>
<div class="result-display">
	수정할 글 정보 호출 실패<br>
	<input type="button" value="게시판 목록" onclick="location.href='selectTest.jsp'">
</div>
<%
		}
	} catch(Exception e){
%>
<div class="result-display">
	오류 발생! 수절할 글 정보 호출 실패!<br>
	<input type="button" value="게시판 목록" onclick="location.href='selectTest.jsp'">
</div>
<%
		e.printStackTrace();

	} finally{
		//자원정리
		if(rs!=null) try{rs.close();}catch(SQLException e){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		if(conn!=null) try{conn.close();}catch(SQLException e){}
	}
%>
</body>
</html>