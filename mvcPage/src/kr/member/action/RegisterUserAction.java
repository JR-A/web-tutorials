package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//�ڹٺ�(VO) ����
		MemberVO member = new MemberVO();
		//���۵� ������ ��ȯ, �ڹٺ��� �����ؼ� �ڹٺ��� ������Ƽ�� ������ ����
		member.setId(request.getParameter("id"));
		member.setName(request.getParameter("name"));
		member.setPasswd(request.getParameter("passwd"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		//ȸ�� ����
		dao.insertMember(member);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
