package kr.board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.util.DBUtil;
import kr.util.FileUtil;
import kr.util.StringUtil;

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
	 */
	//글 등록
	public void insertBoard(BoardVO board)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			//hit, reg_date, modify_date는 테이블 정의에 따라 default값 들어감(0, 현재시간, 현재시간)
			sql = "INSERT INTO board (board_num, title, content, filename, ip, mem_num) VALUES (board_seq.nextval, ?, ?, ?, ?, ?)";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getIp());
			pstmt.setInt(5, board.getMem_num());
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//전체 글 개수, 검색글 개수 (총 레코드 수) -> 페이징처리
	public int getBoardCount(String keyfield, String keyword)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		
		int count = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				//전체 글 개수
				sql = "SELECT COUNT(*) FROM board b JOIN member m ON b.mem_num = m.mem_num"; 	//SQL문 작성
	
				pstmt = conn.prepareStatement(sql); 											//JDBC 수행 3단계 : PreparedStatement 객체 생성
			}else {
				//검색 글 개수
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";		//제목 검색
				else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";		//작성자 검색
				else if(keyfield.equals("3")) sub_sql = "b.content LIKE ?"; //내용 검색
				
				sql = "SELECT COUNT(*) FROM board b JOIN member m ON b.mem_num = m.mem_num WHERE " + sub_sql; //SQL문 작성
				
				pstmt = conn.prepareStatement(sql); 											//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt.setString(1, '%'+keyword+'%');											//(?에 데이터 바인딩) keyword 존재하는 레코드 검색
			}
				
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//COUNT는 집계함수이므로 무조건 하나의 레코드
				count = rs.getInt("COUNT(*)");
				//count = rs.getInt(1);		//컬럼 하나만 만들어지므로 컬럼인덱스 1로 호출 가능
			}
	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//전체 목록, 검색글 목록
	public List<BoardVO> getListBoard(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		
		List<BoardVO> list = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				//전체 목록
																							//SQL문 작성			- id구하기 위해 join.글번호로 내림차순 정렬(최근글이 상위)
				sql = "SELECT * FROM "
					+ 	"(SELECT ROWNUM rnum, a.* FROM "
					+	 	"(SELECT * FROM board b JOIN member m ON b.mem_num = m.mem_num "
					+ 		"ORDER BY board_num DESC) a ) "
					+ "WHERE rnum >= ? AND rnum <= ?";
					/*	
					 *  ROWNUM으로 행에 번호를 붙임. subquery를 테이블처럼 인식하므로 a라고 테이블 알리아스 지정. a.*로 테이블의 모든 컬럼 출력
					 *	ROWNUM에 rnum 알리아스 지정하고 WHERE절에서 rnum 조건 걸어 페이징 처리 
					 */
				//조인조건(ON)에 사용되는 컬럼명이 동일한 경우, 중복컬럼 제거하고 JOIN하려면 ON 대신 USING 키워드 사용함. 대신 테이블 알리아스 사용 불가
				//ON b.mem_num = m.mem_num -> USING(mem_num)
				
				
				pstmt = conn.prepareStatement(sql);											//JDBC 수행 3단계 : PreparedStatement 객체 생성					
				pstmt.setInt(1, start);														//(?에 데이터 바인딩) start~end에 해당하는 ROWNUM의 레코드 가져오기 
				pstmt.setInt(2, end);	
				
			}else {
				//검색글 목록
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";		//제목 검색
				else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";		//작성자 검색
				else if(keyfield.equals("3")) sub_sql = "b.content LIKE ?"; //내용 검색
				
				sql = "SELECT * FROM "														//SQL문 작성
					+ 	"(SELECT ROWNUM rnum, a.* FROM "
					+	 	"(SELECT * FROM board b JOIN member m ON b.mem_num = m.mem_num "
					+ 		"WHERE " + sub_sql + " "										//검색조건 추가!
					+ 		"ORDER BY board_num DESC) a ) "
					+ "WHERE rnum >= ? AND rnum <= ?"; 
				
				pstmt = conn.prepareStatement(sql); 										//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt.setString(1, '%'+keyword+'%');										//(?에 데이터 바인딩) keyfield 컬럼에 keyword 존재하는 레코드 검색
				pstmt.setInt(2, start);														//				 start~end에 해당하는 ROWNUM의 레코드 가져오기
				pstmt.setInt(3, end);
			}
		
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			//ArrayList 생성
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				//자바빈 생성
				BoardVO board = new BoardVO();
				//자바빈의 프로퍼티에 데이터 추가
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHtml(rs.getString("title")));	//제목 - HTML 태그 불허, 줄바꿈 불허
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setModify_date(rs.getDate("modify_date"));
				board.setIp(rs.getString("ip"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				
				//자바빈을 ArrayList에 추가
				list.add(board);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}

	//글 상세(글 번호로 글 정보 가져오기)
	public BoardVO getBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		BoardVO board = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM board b JOIN member m USING(mem_num) WHERE board_num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setInt(1, board_num);
			
			//JDBC 수행 4단계 : SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				//데이터 반환
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				Date reg_date = rs.getDate("reg_date");
				Date modify_date = rs.getDate("modify_date");
				String filename = rs.getString("filename");
				String ip = rs.getString("ip");
				int mem_num = rs.getInt("mem_num");
				String id = rs.getString("id");
				
				//자바빈 생성
				board = new BoardVO(board_num, title, content, hit, reg_date, modify_date, filename, ip, mem_num, id);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}		
		
		return board;
	}
	
	//글 조회수 증가
	public void updateReadCount(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
	
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "UPDATE board SET hit = hit+1 WHERE board_num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setInt(1, board_num);
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			if(board.getFilename() != null) {	//새로 업로드된 파일이 있는 경우
				//SQL문 작성
				sql = "UPDATE board SET title=?, content=?, filename=?, ip=?, modify_date=SYSDATE WHERE board_num = ?";
			
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//(?에 데이터 바인딩)
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getFilename());
				pstmt.setString(4, board.getIp());
				pstmt.setInt(5, board.getBoard_num());
				
			}else {								//새로 업로드한 파일이 없는 경우(기존 파일을 그대로 사용하는 경우)
				//SQL문 작성
				sql = "UPDATE board SET title=?, content=?, ip=?, modify_date=SYSDATE WHERE board_num = ?";
			
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//(?에 데이터 바인딩)
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getIp());
				pstmt.setInt(4, board.getBoard_num());
			}
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			//오류발생시 업로드된 파일 제거
			if(board.getFilename() != null) {
				FileUtil.removeFile(board.getFilename());
			}
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM board WHERE board_num = ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//(?에 데이터 바인딩)
			pstmt.setInt(1, board_num);
			
			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {			
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
