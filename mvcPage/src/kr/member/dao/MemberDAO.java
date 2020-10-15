package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	/*
	 * Singleton Pattern
	 */
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	/*
		1. context.xml에서 설정 정보를 읽어들여 커넥션풀로부터 커넥션을 할당받아 반환(getConnection)
		2. 자원정리(executeClose)
		->	1과 2를 DBUtil.java로 분리
	*/
	
	/*
	 * DB조작
	 */
	/*
	 * JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
	 * SQL문 작성
	 * JDBC 수행 3단계 : PreparedStatement 객체 생성
	 * (?에 데이터 바인딩)
	 * JDBC 수행 4단계 : SQL문 실행
	 * 
	 */
	//회원 가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;		//회원 추가시 테이블이 2개이므로 실행할 sql문이 여러개임
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		
		int num = 0;	//시퀀스 번호 보관
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//오토 커밋 해제(3개의 sql문이 모두 성공했을때만 commit. 하나라도 실패하면 rollback)
			conn.setAutoCommit(false);
			
			//회원 번호 구하기
			sql = "SELECT member_seq.nextval FROM dual"; //SQL문 작성
			pstmt = conn.prepareStatement(sql);			 //JDBC 수행 3단계 : PreparedStatement 객체 생성
			rs = pstmt.executeQuery();					 //JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			//member 테이블에 데이터 저장
			sql = "INSERT INTO member (mem_num, id) VALUES(?, ?)";	//SQL문 작성 (auth는 디폴트값 2 들어감)
			pstmt2 = conn.prepareStatement(sql); 	//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt2.setInt(1, num); 				 	//(?에 데이터 바인딩)
			pstmt2.setString(2, member.getId());
			pstmt2.executeUpdate();				 	//JDBC 수행 4단계 : SQL문 실행
			
			//member_detail 테이블에 데이터 저장
			sql = "INSERT INTO member_detail (mem_num, name, passwd, phone, email, zipcode, address1, address2) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 	//SQL문 작성 (reg_date, modify_date는 디폴트값 SYSDATE 들어감)
			pstmt3 = conn.prepareStatement(sql);			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt3.setInt(1, num);							//(?에 데이터 바인딩)
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getZipcode());
			pstmt3.setString(7, member.getAddress1());
			pstmt3.setString(8, member.getAddress2());
			pstmt3.executeUpdate();							//JDBC 수행 4단계 : SQL문 실행
			
			//sql문 3개 모두 정상적으로 수행되면 commit
			conn.commit();
			
		}catch(Exception e) {
			//예외가 발생하면 rollback
			conn.rollback();
			throw new Exception(e);	//e.printStackTrace()하는 대신 에러를 throw하여 에러페이지 보여주기
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//ID 중복 체크 및 로그인 처리(DB에 회원이 존재하는지 체크)
	public MemberVO checkMember(String id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();

			//SQL문 작성
			//조인시 누락된 행도 출력해야하기 때문에(회원 탈퇴의 경우 member_detail에선 정보가 사라지므로) LEFT OUTER JOIN 해야함(누락된 행의 반대반향 표시)
			sql = "SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.id = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setString(1, id);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//아이디가 존재하면(중복이면)
				//자바빈 생성
				member = new MemberVO();
				//자바빈의 프로퍼티에 DB정보를 저장
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//회원 상세 정보(회원 번호로 회원 정보 가져오기)
	public MemberVO getMember(int mem_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			//member, member_detail을 INNER JOIN
			sql = "SELECT * FROM member m INNER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num = ?";
			//조인조건(ON)에 사용되는 컬럼명이 동일한 경우, 중복컬럼 제거하고 JOIN하려면 ON 대신 USING 키워드 사용함. 대신 테이블 알리아스 사용 불가
			//sql = "SELECT * FROM member m INNER JOIN member_detail d USING(mem_num) WHERE mem_num = ?";			
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setInt(1, mem_num);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//자바빈 생성
				member = new MemberVO();
				//자바빈의 프로퍼티에 DB정보를 저장
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setName(rs.getString("name"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setModify_date(rs.getDate("modify_date"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//비밀번호 수정
	public void updatePassword(String passwd, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "UPDATE member_detail SET passwd=? WHERE mem_num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setString(1, passwd);
			pstmt.setInt(2, mem_num);
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//회원 정보 수정
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "UPDATE member_detail SET name=?, phone=?, email=?, zipcode=?, address1=?, address2=?, modify_date=SYSDATE WHERE mem_num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setInt(7, member.getMem_num());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//회원 탈퇴, 회원 정보 삭제
	public void deleteMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//오토 커밋 해제(2개의 sql문이 모두 성공했을때만 commit. 하나라도 실패하면 rollback)
			conn.setAutoCommit(false);
			
			//회원 정보 삭제
			sql = "DELETE FROM member_detail WHERE mem_num=?";	//SQL문 작성
			pstmt = conn.prepareStatement(sql);					//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt.setInt(1, mem_num);							//(?에 데이터 바인딩)
			pstmt.executeUpdate();								//JDBC 수행 4단계 : SQL문 실행
			
			//권한변경(auth->0) 									/*0:탈퇴회원, 1:정지회원, 2:일반회원, 3:관리자*/
			sql = "UPDATE member SET auth=0 WHERE mem_num=?";	//SQL문 작성
			pstmt2 = conn.prepareStatement(sql);				//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt2.setInt(1, mem_num);							//(?에 데이터 바인딩)
			pstmt2.executeUpdate();								//JDBC 수행 4단계 : SQL문 실행
			
			//sql문 2개 모두 정상적으로 수행되면 commit
			conn.commit();
			
		}catch(Exception e) {
			//예외가 발생하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
