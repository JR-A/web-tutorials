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
	 * Singleton Pattern(�̱��� ����)
	 *  : �����ڸ� private���� �Ͽ� �ܺο��� ȣ���� �� ������ �ϰ�, 
	 *    static �޼��带 ȣ���Ͽ� ��ü�� �� ���� �����ǰ� ������ ��ü�� ������ �� �ֵ��� �ϴ� ���
	 * 
	 */
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}	//�����ڸ� private���� ����
	
	
	/*
	 * context.xml���� ���� ������ �о�鿩 Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ�޾� ��ȯ 
	 */
	private Connection getConnection() throws Exception{
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
	//ȸ������
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "INSERT INTO smember (num, id, name, passwd, email, phone, reg_date) VALUES(member_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
	}
	
	//ȸ�� �� ����(ȸ�� ��ȣ�� ȸ�� ���� ��������)
	public MemberVO getMember(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql  = "SELECT * FROM smember WHERE num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setInt(1, num);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//������� 1���̰ų� 0��
				member = new MemberVO(); //�ڹٺ� ��ü ����
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));	//���������� �� Ȱ�� ���
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setReg_date(rs.getDate("reg_date"));	//���������� �� Ȱ�� ���
			}
			
		} catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		} finally{
			//�ڿ�����
			executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//���̵� �ߺ� üũ, �α��� üũ(ȸ���� �����ϴ��� üũ)
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql  = "SELECT * FROM smember WHERE id=?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, id);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//������� 1���̰ų� 0��
				member = new MemberVO();	//�ڹٺ� ��ü ����
				member.setId(rs.getString("id"));
				member.setNum(rs.getInt("num"));
				member.setPasswd(rs.getString("passwd"));
			}
			
		} catch(Exception e) {
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		} finally {
			//�ڿ�����
			executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//ȸ�� ���� ����
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "UPDATE smember SET name=?, passwd=?, email=?, phone=? WHERE num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5, member.getNum());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
	}
	
	//ȸ�� Ż��(ȸ�� ���� ����)
	public void deleteMember(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = getConnection();
			
			//SQL�� �ۼ�
			sql = "DELETE FROM smember WHERE num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);			
			//?�� ������ ���ε�
			pstmt.setInt(1, num);
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e); //e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			executeClose(null, pstmt, conn);
		}
	}
}
