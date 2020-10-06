package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.member.vo.MemberVO;

//DAO : Data Access Object
public class MemberDAO {
	/*
	 * Singleton Pattern(싱글턴 패턴)
	 *  : 생성자를 private으로 하여 외부에서 호출할 수 없도록 하고, 
	 *    static 메서드를 호출하여 객체가 한 번만 생성되고 생성된 객체를 공유할 수 있도록 하는 방식
	 * 
	 */
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}	//생성자를 private으로 선언
	
	
	/*
	 * context.xml에서 설정 정보를 읽어들여 커넥션풀로부터 커넥션을 할당받아 반환 
	 */
	private Connection getConnection() throws Exception{
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
	//회원가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "INSERT INTO smember (num, id, name, passwd, email, phone, reg_date) VALUES(member_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
	}
	
	//회원 상세 정보(회원 번호로 회원 정보 가져오기)
	public MemberVO getMember(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql  = "SELECT * FROM smember WHERE num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//결과행이 1개이거나 0개
				member = new MemberVO(); //자바빈 객체 생성
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));	//마이페이지 등 활용 대비
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setReg_date(rs.getDate("reg_date"));	//마이페이지 등 활용 대비
			}
			
		} catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		} finally{
			//자원정리
			executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//아이디 중복 체크, 로그인 체크(회원이 존재하는지 체크)
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql  = "SELECT * FROM smember WHERE id=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//결과행이 1개이거나 0개
				member = new MemberVO();	//자바빈 객체 생성
				member.setId(rs.getString("id"));
				member.setNum(rs.getInt("num"));
				member.setPasswd(rs.getString("passwd"));
			}
			
		} catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		} finally {
			//자원정리
			executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//회원 정보 수정
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = getConnection();
			
			//SQL문 작성
			sql = "UPDATE smember SET name=?, passwd=?, email=?, phone=? WHERE num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5, member.getNum());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
	}
	
	//회원 탈퇴(회원 정보 삭제)
	public void deleteMember(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받은
			conn = getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM smember WHERE num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);			
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			executeClose(null, pstmt, conn);
		}
	}
}
