package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request�� ������ ����
		request.setAttribute("message", "�Խ��� �������Դϴ�.");
		
		//JSP ��� ��ȯ
		return "/views/detail.jsp";
	}

}
