<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %> <%-- db연동시 필요한 자원들 --%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>

<%@ include file="dbInfo.jspf" %> <%-- jsp파일에 dbInfo.jspf를 삽입해서 변수 공유. 반복적으로 쓰이는 db정보 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='insertTestForm.jsp'">
	</div>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
<%	
	//DB 연동
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(driverName);
		
		//JDBC 수행 2단계 : Connection 객체 생성(db ID와 비밀번호로 인증)
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		//SQL문 작성 (최신 글일수록 상단에 올라가도록 내림차순 정렬)
		sql = "SELECT * FROM tboard ORDER BY num DESC";
		
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		
		//JDBC 수행 4단계 : SQL문 실행, 테이블에 반영하고 결과행을 ResultSet에 담음
		rs = pstmt.executeQuery();
		while(rs.next()){
			int num = rs.getInt("num");
			String name = rs.getString("name");
			String title = rs.getString("title");
			Date reg_date = rs.getDate("reg_date");		
%>
		<tr>
			<td><%= num %></td>
			<%-- 제목 클릭시 해당 글의 상세페이지로 이동. 하나의 레코드 정보 열람 위해 primary key인 num 넘겨줌 --%>
			<td><a href="detailTest.jsp?num=<%= num %>"><%= title %></a></td>
			<td><%= name %></td>
			<td><%= reg_date %></td>
		</tr>
<%
		}
		
	} catch(Exception e){
%>
		<tr>
			<%-- 셀 4개를 병합 --%>
			<td colspan="4" class="align-center">
				 오류 발생!
			</td>
		</tr>
<%
		e.printStackTrace(); //콘솔상 에러메시지 출력
			
	} finally{
		//자원정리
		if(rs!=null) try{rs.close();}catch(SQLException e){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		if(conn!=null) try{conn.close();}catch(SQLException e){}
	}
%>
	</table>
</div>
</body>
</html>