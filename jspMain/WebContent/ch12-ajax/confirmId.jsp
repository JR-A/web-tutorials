<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.DriverManager" %> <%-- db연동시 필요한 자원들 --%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>

<%@ include file="dbInfo.jspf" %> <%-- jsp파일에 dbInfo.jspf를 삽입해서 변수 공유. 반복적으로 쓰이는 db정보 --%>

<%-- register.html 에서 실행 --%>
<%-- get방식 테스트 방법 : 실행 후 url에 '?id=값' 추가 --%>

<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	//DB연동
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(driverName);
		
		//JDBC 수행 2단계 : Connnection 객체 생성(db ID와 비밀번호로 인증)
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		//SQL문 작성
		sql = "SELECT id FROM People WHERE id = ?";
		
		//JDBC 수행 3단계 : PreparedStatemet 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setString(1, id);
		
		//JDBC 수행 4단계 : SQL문 실행, 테이블에 반영하고 결과행을 ResultSet에 담음
		rs = pstmt.executeQuery();
		if(rs.next()){	//id 중복된 경우
%>
			<%-- JSON 만들기 --%>
			{
				"result" : "success",
				"id" : "idDuplicated"
			}
<%
		} else{ //id 중복되지 않은 경우(존재하지 않는 경우)
%>
			<%-- JSON 만들기 --%>
			{
				"result" : "success",
				"id" : "idNotFound"
			}
<%
		}
		
	} catch(Exception e){
%>
		<%-- JSON 만들기 --%>
		{
			"result" : "failure"
		}
<%
		e.printStackTrace(); //콘솔창 에러메시지 출력
		
	} finally{
		//자원정리
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
%>