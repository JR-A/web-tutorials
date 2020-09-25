<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %> <%-- db연동시 필요한 자원들 --%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<%@ include file="dbInfo.jspf" %> <%-- jsp파일에 dbInfo.jspf를 삽입해서 변수 공유. 반복적으로 쓰이는 db정보 --%>

    <%-- insertTestForm.jsp 에서 실행 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록 처리</title>
<link rel="stylesheet" href="style.css" type="text/css">

</head>
<body>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8"); 
	
	//전송된 데이터 반환
	String name = request.getParameter("name");  //insertTestForm로부터 전달받음(post방식)
	String title = request.getParameter("title");
	String passwd = request.getParameter("passwd");
	String content = request.getParameter("content");
	
	//DB연동
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(driverName);		
			
		//JDBC 수행 2단계 : Connection 객체 생성(db ID와 비밀번호로 인증)
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		//SQL문 작성
		sql = "INSERT INTO tboard (num, name, title, passwd, content, reg_date) VALUES (board_seq.nextval, ?, ?, ?, ?, SYSDATE)";
		
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setString(1, name);
		pstmt.setString(2, title);
		pstmt.setString(3, passwd);
		pstmt.setString(4, content);
		
		//JDBC 수행 4단계 : SQL문 실행
		pstmt.executeUpdate();
%>
		<div class="result-display">
			글 등록 성공<br>
			<input type="button" value="목록보기" onclick="location.href='selectTest.jsp'">
		</div>
<%	
	} catch(Exception e){
%>
		<div class="result-display">
			오류 발생! 글 등록 실패!<br>
			<input type="button" value="글쓰기" onclick="location.href='insertTestForm.jsp'">
		</div>
<%		
		e.printStackTrace();  //콘솔상 에러메시지 출력
	} finally{
		//자원 정리
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		if(conn!=null) try{conn.close();}catch(SQLException e){}
	}
	
%>
</body>
</html>