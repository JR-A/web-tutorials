package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;
	private String id;
	private int auth;
	private String name;
	private String passwd;
	private String phone;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private Date reg_date;
	private Date modify_date;
	
	//������
	public MemberVO(){}
	
	public MemberVO(int mem_num, String name, String phone, String email, String zipcode, String address1, String address2){
		this.mem_num = mem_num;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}
	
	//��й�ȣ ��ġ ���� üũ
	public boolean isCheckedPasswd(String userPasswd) {
		//auth -> 0:Ż��ȸ��, 1:����ȸ��, 2:�Ϲ�ȸ��, 3:������
		if(auth > 1 && passwd.equals(userPasswd)) {	//�Ϲ�ȸ���̰ų� �������� ��� && ��й�ȣ ��ġ�ϴ� ���
			return true;
		}
		return false;
	}
	
	//Getters and Setters
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
}
