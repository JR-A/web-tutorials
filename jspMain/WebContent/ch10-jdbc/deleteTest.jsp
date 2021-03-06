<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %> <%-- db연동시 필요한 자원들 --%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<%@ include file="dbInfo.jspf" %> <%-- jsp파일에 dbInfo.jspf를 삽입해서 변수 공유. 반복적으로 쓰이는 db정보 --%>
    
    <%-- selectTest.jsp에서 실행하여 글 목록 불러오고, 특정 게시물 선택해서 detailTest.jsp 실행 후 삭제 버튼 클릭 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<%
	//전송된 데이터 인코딩 처리(post방식)
	request.setCharacterEncoding("utf-8");

	//전송된 데이터 반환
	int num = Integer.parseInt(request.getParameter("num"));	//deleteTestForm.jsp에서 전달받음
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(driverName);
		
		//JDBC 수행 2단계 : Connection 객체 생성(db ID와 비밀번호로 인증)
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		//SQL문 작성
		sql = "DELETE FROM tboard WHERE num = ?";
		
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setInt(1, num);
		
		//JDBC 수행 4단계 : SQL문 실행, 테이블에 반영
		pstmt.executeUpdate();
%>
<div class="result-display">
	글 삭제 완료!<br>
	<input type="button" value="목록 보기" onclick="location.href='selectTest.jsp'">
</div>
<%
	} catch(Exception e){
%>
<div class="result-display">
	오류 발생! 글 삭제 실패!<br>
	<input type="button" value="목록 보기" onclick="location.href='selectTest.jsp'">
</div>
<%
		e.printStackTrace();

	} finally{
		//자원정리
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		if(conn!=null) try{conn.close();}catch(SQLException e){}
	}
%>
</body>
</html>