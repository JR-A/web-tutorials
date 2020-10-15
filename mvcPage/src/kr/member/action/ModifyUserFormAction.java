package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			//�α��� �� ȣ��
			return "redirect:/member/loginForm.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
		}
		
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num); //ȸ����ȣ�� �α����� ȸ�� ���� ��������
		
		request.setAttribute("member", member);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/member/modifyUserForm.jsp";
	}

}
