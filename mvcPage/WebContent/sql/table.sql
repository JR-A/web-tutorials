/*==========회원관리===========*/
CREATE TABLE member(
	mem_num NUMBER NOT NULL,
	id VARCHAR2(12) UNIQUE NOT NULL,
	auth NUMBER(1) DEFAULT 2 NOT NULL, /*0:탈퇴회원, 1:정지회원, 2:일반회원, 3:관리자*/
	
	CONSTRAINT member_pk PRIMARY KEY (mem_num)
);
CREATE TABLE member_detail(
	mem_num NUMBER NOT NULL,
	name VARCHAR2(30) NOT NULL,
	passwd VARCHAR2(12) NOT NULL,
	phone VARCHAR2(15) NOT NULL,
	email VARCHAR2(50) NOT NULL,
	zipcode VARCHAR2(5) NOT NULL,
	address1 VARCHAR2(90) NOT NULL,
	address2 VARCHAR2(90),
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT member_detail_pk PRIMARY KEY (mem_num),
	CONSTRAINT member_detail_fk FOREIGN KEY (mem_num) REFERENCES member (mem_num)
);

CREATE SEQUENCE member_seq;