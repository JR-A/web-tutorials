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
	 * 
	 */
	//ȸ�� ����
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;		//ȸ�� �߰��� ���̺��� 2���̹Ƿ� ������ sql���� ��������
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		
		int num = 0;	//������ ��ȣ ����
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			//���� Ŀ�� ����(3���� sql���� ��� ������������ commit. �ϳ��� �����ϸ� rollback)
			conn.setAutoCommit(false);
			
			//ȸ�� ��ȣ ���ϱ�
			sql = "SELECT member_seq.nextval FROM dual"; //SQL�� �ۼ�
			pstmt = conn.prepareStatement(sql);			 //JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			rs = pstmt.executeQuery();					 //JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			//member ���̺� ������ ����
			sql = "INSERT INTO member (mem_num, id) VALUES(?, ?)";	//SQL�� �ۼ� (auth�� ����Ʈ�� 2 ��)
			pstmt2 = conn.prepareStatement(sql); 	//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt2.setInt(1, num); 				 	//(?�� ������ ���ε�)
			pstmt2.setString(2, member.getId());
			pstmt2.executeUpdate();				 	//JDBC ���� 4�ܰ� : SQL�� ����
			
			//member_detail ���̺� ������ ����
			sql = "INSERT INTO member_detail (mem_num, name, passwd, phone, email, zipcode, address1, address2) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 	//SQL�� �ۼ� (reg_date, modify_date�� ����Ʈ�� SYSDATE ��)
			pstmt3 = conn.prepareStatement(sql);			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt3.setInt(1, num);							//(?�� ������ ���ε�)
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getZipcode());
			pstmt3.setString(7, member.getAddress1());
			pstmt3.setString(8, member.getAddress2());
			pstmt3.executeUpdate();							//JDBC ���� 4�ܰ� : SQL�� ����
			
			//sql�� 3�� ��� ���������� ����Ǹ� commit
			conn.commit();
			
		}catch(Exception e) {
			//���ܰ� �߻��ϸ� rollback
			conn.rollback();
			throw new Exception(e);	//e.printStackTrace()�ϴ� ��� ������ throw�Ͽ� ���������� �����ֱ�
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//ID �ߺ� üũ �� �α��� ó��(DB�� ȸ���� �����ϴ��� üũ)
	public MemberVO checkMember(String id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();

			//SQL�� �ۼ�
			//���ν� ������ �൵ ����ؾ��ϱ� ������(ȸ�� Ż���� ��� member_detail���� ������ ������Ƿ�) LEFT OUTER JOIN �ؾ���(������ ���� �ݴ���� ǥ��)
			sql = "SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.id = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setString(1, id);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//���̵� �����ϸ�(�ߺ��̸�)
				//�ڹٺ� ����
				member = new MemberVO();
				//�ڹٺ��� ������Ƽ�� DB������ ����
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//ȸ�� �� ����(ȸ�� ��ȣ�� ȸ�� ���� ��������)
	public MemberVO getMember(int mem_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberVO member = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			//member, member_detail�� INNER JOIN
			sql = "SELECT * FROM member m INNER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num = ?";
			//��������(ON)�� ���Ǵ� �÷����� ������ ���, �ߺ��÷� �����ϰ� JOIN�Ϸ��� ON ��� USING Ű���� �����. ��� ���̺� �˸��ƽ� ��� �Ұ�
			//sql = "SELECT * FROM member m INNER JOIN member_detail d USING(mem_num) WHERE mem_num = ?";			
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setInt(1, mem_num);
			
			//JDBC ���� 4�ܰ� : SQL�� �����ϰ� ������� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//�ڹٺ� ����
				member = new MemberVO();
				//�ڹٺ��� ������Ƽ�� DB������ ����
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
			//�ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//��й�ȣ ����
	public void updatePassword(String passwd, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "UPDATE member_detail SET passwd=? WHERE mem_num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setString(1, passwd);
			pstmt.setInt(2, mem_num);
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//ȸ�� ���� ����
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "UPDATE member_detail SET name=?, phone=?, email=?, zipcode=?, address1=?, address2=?, modify_date=SYSDATE WHERE mem_num = ?";
			
			//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//(?�� ������ ���ε�)
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setInt(7, member.getMem_num());
			
			//JDBC ���� 4�ܰ� : SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//ȸ�� Ż��, ȸ�� ���� ����
	public void deleteMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			//JDBC ���� 1,2�ܰ� : Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ����
			conn = DBUtil.getConnection();
			//���� Ŀ�� ����(2���� sql���� ��� ������������ commit. �ϳ��� �����ϸ� rollback)
			conn.setAutoCommit(false);
			
			//ȸ�� ���� ����
			sql = "DELETE FROM member_detail WHERE mem_num=?";	//SQL�� �ۼ�
			pstmt = conn.prepareStatement(sql);					//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt.setInt(1, mem_num);							//(?�� ������ ���ε�)
			pstmt.executeUpdate();								//JDBC ���� 4�ܰ� : SQL�� ����
			
			//���Ѻ���(auth->0) 									/*0:Ż��ȸ��, 1:����ȸ��, 2:�Ϲ�ȸ��, 3:������*/
			sql = "UPDATE member SET auth=0 WHERE mem_num=?";	//SQL�� �ۼ�
			pstmt2 = conn.prepareStatement(sql);				//JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			pstmt2.setInt(1, mem_num);							//(?�� ������ ���ε�)
			pstmt2.executeUpdate();								//JDBC ���� 4�ܰ� : SQL�� ����
			
			//sql�� 2�� ��� ���������� ����Ǹ� commit
			conn.commit();
			
		}catch(Exception e) {
			//���ܰ� �߻��ϸ� rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			//�ڿ�����
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
