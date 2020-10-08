package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request�� ������ ����
		request.setAttribute("message", "���ο� �Խù��� ����߽��ϴ�.");
		
		//JSP ��� ��ȯ
		return "/views/write.jsp";
	}

}
