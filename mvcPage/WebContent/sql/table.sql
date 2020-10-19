/*==========ȸ������===========*/
CREATE TABLE member(
	mem_num NUMBER NOT NULL,
	id VARCHAR2(12) UNIQUE NOT NULL,
	auth NUMBER(1) DEFAULT 2 NOT NULL, /*0:Ż��ȸ��, 1:����ȸ��, 2:�Ϲ�ȸ��, 3:������*/
	
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

/*==========�Խ��ǰ���===========*/
CREATE TABLE board(
	board_num NUMBER NOT NULL,
	title VARCHAR2(150) NOT NULL,	/*VARCHAR/VARCHAR2 �� �ִ� 4000byte���� ���� ����*/
	content CLOB NOT NULL, 			/*��뷮 �ؽ�Ʈ �����͸� �ܺ� ���Ϸ� �����ϱ� ���� ������ Ÿ��. �ִ� 4GB*/
	hit NUMBER(5) DEFAULT 0 NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	filename VARCHAR2(150),
	ip VARCHAR2(40) NOT NULL,
	mem_num NUMBER NOT NULL,
	
	CONSTRAINT board_pk PRIMARY KEY (board_num),
	CONSTRAINT board_fk FOREIGN KEY (mem_num) REFERENCES member (mem_num)
);

CREATE SEQUENCE board_seq;
