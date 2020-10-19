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
		1. context.xml���� ���� ������ �о�鿩 Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ�޾� ��ȯ(getConnection)
		2. �ڿ�����(executeClose)
		->	1�� 2�� DBUtil.java�� �и�
	 */

	/*
	 * DB����
	 */
	/*
	 * JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
	 * SQL�� �ۼ�
	 * JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
	 * (?�� ������ ���ε�)
	 * JDBC ���� 4�ܰ� : SQL�� ����
	 */
	//�� ���
	public void insertBoard(BoardVO board)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			//hit, reg_date, modify_date�� ���̺� ���ǿ� ���� default�� ��(0, ����ð�, ����ð�)
			sql = "INSERT INTO board (board_num, title, content, filename, ip, mem_num) VALUES (board_seq.nextval, ?, ?, ?, ?, ?)";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getIp());
			pstmt.setInt(5, board.getMem_num());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//��ü �� ����, �˻��� ���� (�� ���ڵ� ��) -> ����¡ó��
	public int getBoardCount(String keyfield, String keyword)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		
		int count = 0;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				//��ü �� ����
				sql = "SELECT COUNT(*) FROM board b JOIN member m ON b.mem_num = m.mem_num"; 	//SQL�� �ۼ�
	
				pstmt = conn.prepareStatement(sql); 											//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			}else {
				//�˻� �� ����
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";		//���� �˻�
				else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";		//�ۼ��� �˻�
				else if(keyfield.equals("3")) sub_sql = "b.content LIKE ?"; //���� �˻�
				
				sql = "SELECT COUNT(*) FROM board b JOIN member m ON b.mem_num = m.mem_num WHERE " + sub_sql; //SQL�� �ۼ�
				
				pstmt = conn.prepareStatement(sql); 											//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
				pstmt.setString(1, '%'+keyword+'%');											//(?�� ������ ���ε�) keyword �����ϴ� ���ڵ� �˻�
			}
				
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//COUNT�� �����Լ��̹Ƿ� ������ �ϳ��� ���ڵ�
				count = rs.getInt("COUNT(*)");
				//count = rs.getInt(1);		//�÷� �ϳ��� ��������Ƿ� �÷��ε��� 1�� ȣ�� ����
			}
	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//��ü ���, �˻��� ���
	public List<BoardVO> getListBoard(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		
		List<BoardVO> list = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				//��ü ���
																							//SQL�� �ۼ�			- id���ϱ� ���� join.�۹�ȣ�� �������� ����(�ֱٱ��� ����)
				sql = "SELECT * FROM "
					+ 	"(SELECT ROWNUM rnum, a.* FROM "
					+	 	"(SELECT * FROM board b JOIN member m ON b.mem_num = m.mem_num "
					+ 		"ORDER BY board_num DESC) a ) "
					+ "WHERE rnum >= ? AND rnum <= ?";
					/*	
					 *  ROWNUM���� �࿡ ��ȣ�� ����. subquery�� ���̺�ó�� �ν��ϹǷ� a��� ���̺� �˸��ƽ� ����. a.*�� ���̺��� ��� �÷� ���
					 *	ROWNUM�� rnum �˸��ƽ� �����ϰ� WHERE������ rnum ���� �ɾ� ����¡ ó�� 
					 */
				//��������(ON)�� ���Ǵ� �÷����� ������ ���, �ߺ��÷� �����ϰ� JOIN�Ϸ��� ON ��� USING Ű���� �����. ��� ���̺� �˸��ƽ� ��� �Ұ�
				//ON b.mem_num = m.mem_num -> USING(mem_num)
				
				
				pstmt = conn.prepareStatement(sql);											//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����					
				pstmt.setInt(1, start);														//(?�� ������ ���ε�) start~end�� �ش��ϴ� ROWNUM�� ���ڵ� �������� 
				pstmt.setInt(2, end);	
				
			}else {
				//�˻��� ���
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";		//���� �˻�
				else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";		//�ۼ��� �˻�
				else if(keyfield.equals("3")) sub_sql = "b.content LIKE ?"; //���� �˻�
				
				sql = "SELECT * FROM "														//SQL�� �ۼ�
					+ 	"(SELECT ROWNUM rnum, a.* FROM "
					+	 	"(SELECT * FROM board b JOIN member m ON b.mem_num = m.mem_num "
					+ 		"WHERE " + sub_sql + " "										//�˻����� �߰�!
					+ 		"ORDER BY board_num DESC) a ) "
					+ "WHERE rnum >= ? AND rnum <= ?"; 
				
				pstmt = conn.prepareStatement(sql); 										//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
				pstmt.setString(1, '%'+keyword+'%');										//(?�� ������ ���ε�) keyfield �÷��� keyword �����ϴ� ���ڵ� �˻�
				pstmt.setInt(2, start);														//				 start~end�� �ش��ϴ� ROWNUM�� ���ڵ� ��������
				pstmt.setInt(3, end);
			}
		
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			//ArrayList ����
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				//�ڹٺ� ����
				BoardVO board = new BoardVO();
				//�ڹٺ��� ������Ƽ�� ������ �߰�
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHtml(rs.getString("title")));	//���� - HTML �±� ����, �ٹٲ� ����
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setModify_date(rs.getDate("modify_date"));
				board.setIp(rs.getString("ip"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				
				//�ڹٺ��� ArrayList�� �߰�
				list.add(board);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}

	//�� ��(�� ��ȣ�� �� ���� ��������)
	public BoardVO getBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		BoardVO board = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "SELECT * FROM board b JOIN member m USING(mem_num) WHERE board_num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setInt(1, board_num);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				//������ ��ȯ
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				Date reg_date = rs.getDate("reg_date");
				Date modify_date = rs.getDate("modify_date");
				String filename = rs.getString("filename");
				String ip = rs.getString("ip");
				int mem_num = rs.getInt("mem_num");
				String id = rs.getString("id");
				
				//�ڹٺ� ����
				board = new BoardVO(board_num, title, content, hit, reg_date, modify_date, filename, ip, mem_num, id);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}		
		
		return board;
	}
	
	//�� ��ȸ�� ����
	public void updateReadCount(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
	
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "UPDATE board SET hit = hit+1 WHERE board_num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setInt(1, board_num);
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//�� ����
	public void updateBoard(BoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			if(board.getFilename() != null) {	//���� ���ε�� ������ �ִ� ���
				//SQL�� �ۼ�
				sql = "UPDATE board SET title=?, content=?, filename=?, ip=?, modify_date=SYSDATE WHERE board_num = ?";
			
				//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
				pstmt = conn.prepareStatement(sql);
				//(?�� ������ ���ε�)
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getFilename());
				pstmt.setString(4, board.getIp());
				pstmt.setInt(5, board.getBoard_num());
				
			}else {								//���� ���ε��� ������ ���� ���(���� ������ �״�� ����ϴ� ���)
				//SQL�� �ۼ�
				sql = "UPDATE board SET title=?, content=?, ip=?, modify_date=SYSDATE WHERE board_num = ?";
			
				//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
				pstmt = conn.prepareStatement(sql);
				//(?�� ������ ���ε�)
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getIp());
				pstmt.setInt(4, board.getBoard_num());
			}
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			//�����߻��� ���ε�� ���� ����
			if(board.getFilename() != null) {
				FileUtil.removeFile(board.getFilename());
			}
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//�� ����
	public void deleteBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "DELETE FROM board WHERE board_num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setInt(1, board_num);
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {			
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
