package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

//�� Ŭ����(���� ó���ϰ� JSP ��� ��ȯ)
public class WriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���� ó��
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/writeForm.jsp";
	}

}
