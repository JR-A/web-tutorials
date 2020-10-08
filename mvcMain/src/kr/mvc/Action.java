package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//���� Model Ŭ������ �ϳ��� ���յ� ������Ÿ������ �����ϱ� ���� �������̽� ����
//Servlet���� �ڵ带 ���� ��ġ�� �ʱ�����
public interface Action {
	//�߻�޼���(������ ����. �������̽� implement�ϴ� Ŭ�������� �����ϵ��� ����)
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
}
