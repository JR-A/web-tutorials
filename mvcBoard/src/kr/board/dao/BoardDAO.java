package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.board.vo.BoardVO;

public class BoardDAO {
	/*
	 * Singleton Pattern
	 */
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	/*
	 * context.xml에서 설정 정보를 읽어들여 커넥션풀로부터 커넥션을 할당받아 반환
	 */
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe"); //jdbc/xe는 사용자가 정함
		
		return ds.getConnection();
	}
	
	/*
	 * 자원정리
	 */
	private void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs!=null) try{rs.close();}catch(SQLException e) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e) {}
		if(conn!=null) try {conn.close();}catch(SQLException e) {}
	}
	
	/*
	 * DB조작
	 */
	//글 저장
	public void insert(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "INSERT INTO sboard (num, title, name, passwd, email, content, ip, reg_date) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getPasswd());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setString(5, boardVO.getContent());
			pstmt.setString(6, boardVO.getIp());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}

	}
	
	//글 목록
	public List<BoardVO> getList()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		List<BoardVO> list = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM sboard ORDER BY reg_date DESC"; //작성일 기준 최신 글 순서대로 불러오기
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			//ArrayList 객체 생성
			list = new ArrayList<BoardVO>();
			
			//반복문을 이용해서 데이터를 반환
			while(rs.next()) {
				//자바빈을 생성해서 데이터를 저장
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setName(rs.getString("name"));
				boardVO.setPasswd(rs.getString("passwd"));
				boardVO.setEmail(rs.getString("email"));
				boardVO.setContent(rs.getString("email"));
				boardVO.setIp(rs.getString("ip"));
				boardVO.setReg_date(rs.getDate("reg_date"));
				
				//ArrayList에 자바빈을 저장
				list.add(boardVO);
			}
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
		
		return list;
	}
	
	//글 상세(primary key인 num을 입력받아 board(게시글)반환)
	public BoardVO getBoard(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		BoardVO board = null;
		
		try {	
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM sboard WHERE num = ?";

			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//자바빈을 생성해서 데이터를 저장
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setName(rs.getString("name"));
				board.setPasswd(rs.getString("passwd"));
				board.setEmail(rs.getString("email"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
		
		return board;
	}
	
	//글 수정
	public void update(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "UPDATE sboard SET title=?, name=?, email=?, content=?, ip=? WHERE num=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getEmail());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getIp());
			pstmt.setInt(6, boardVO.getNum());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void delete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM sboard WHERE num=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
	}
	
}