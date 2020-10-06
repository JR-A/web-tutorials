package kr.member.vo;

import java.sql.Date;

//�ڹٺ�(JavaBean, DTO:Data Transfer Object, VO : Value Object)
public class MemberVO {
	private int num;	//ȸ����ȣ
	private String id;  //ȸ��id
	private String name; //ȸ����
	private String passwd; //��й�ȣ
	private String email;  //�̸���
	private String phone;  //��ȭ��ȣ
	private Date reg_date; //������
	
	//��й�ȣ üũ
	public boolean isCheckedPasswd(String userPasswd) {
		if(passwd.equals(userPasswd)) { //��й�ȣ ��ġ
			return true;
		}
		return false;	//��й�ȣ ����ġ
	}
	
	//Getters and Setters
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
