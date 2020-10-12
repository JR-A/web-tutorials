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
	 * context.xml���� ���� ������ �о�鿩 Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ�޾� ��ȯ
	 */
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe"); //jdbc/xe�� ����ڰ� ����
		
		return ds.getConnection();
	}
	
	/*
	 * �ڿ�����
	 */
	private void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs!=null) try{rs.close();}catch(SQLException e) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e) {}
		if(conn!=null) try {conn.close();}catch(SQLException e) {}
	}
	
	/*
	 * DB����
	 */
	//�� ����
	public void insert(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "INSERT INTO sboard (num, title, name, passwd, email, content, ip, reg_date) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getPasswd());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setString(5, boardVO.getContent());
			pstmt.setString(6, boardVO.getIp());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}

	}
	
	//�� ���
	public List<BoardVO> getList()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		List<BoardVO> list = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "SELECT * FROM sboard ORDER BY reg_date DESC"; //�ۼ��� ���� �ֽ� �� ������� �ҷ�����
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			//ArrayList ��ü ����
			list = new ArrayList<BoardVO>();
			
			//�ݺ����� �̿��ؼ� �����͸� ��ȯ
			while(rs.next()) {
				//�ڹٺ��� �����ؼ� �����͸� ����
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setName(rs.getString("name"));
				boardVO.setPasswd(rs.getString("passwd"));
				boardVO.setEmail(rs.getString("email"));
				boardVO.setContent(rs.getString("email"));
				boardVO.setIp(rs.getString("ip"));
				boardVO.setReg_date(rs.getDate("reg_date"));
				
				//ArrayList�� �ڹٺ��� ����
				list.add(boardVO);
			}
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
		
		return list;
	}
	
	//�� ��(primary key�� num�� �Է¹޾� board(�Խñ�)��ȯ)
	public BoardVO getBoard(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		BoardVO board = null;
		
		try {	
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "SELECT * FROM sboard WHERE num = ?";

			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setInt(1, num);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//�ڹٺ��� �����ؼ� �����͸� ����
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
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
		
		return board;
	}
	
	//�� ����
	public void update(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "UPDATE sboard SET title=?, name=?, email=?, content=?, ip=? WHERE num=?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getEmail());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getIp());
			pstmt.setInt(6, boardVO.getNum());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
	}
	
	//�� ����
	public void delete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "DELETE FROM sboard WHERE num=?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setInt(1, num);
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
	}
	
}