package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//����ó��
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//�α��� �� ȣ��
			return "redirect:/member/loginForm.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
		}
		
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		//���۵� ������ ��ȯ
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberVO member = new MemberVO(user_num, name, phone, email, zipcode, address1, address2);	
		
		dao.updateMember(member);	//ȸ�� ���� ����

		//JSP ��� ��ȯ
		return "/WEB-INF/views/member/modifyUser.jsp";
	}

}
