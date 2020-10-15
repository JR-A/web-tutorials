package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//HttpSession ��ȯ
		HttpSession session = request.getSession();
		//�α׾ƿ�ó��
		session.invalidate(); //���� ��ȿȭ
		
		return "redirect:/main/main.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
	}

}
