package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements Action {	//Action�������̽��� �߻�޼��带 �ݵ�� �����ؾ���

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request�� ������ ����
		request.setAttribute("message", "�Խ��� ����Դϴ�.");
		
		//JSP ��� ��ȯ
		return "/views/list.jsp";
	}

}
