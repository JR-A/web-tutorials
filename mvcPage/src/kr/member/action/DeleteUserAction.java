package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class DeleteUserAction implements Action {

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
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		//�Է���id�� �ش��ϴ� ȸ�������� �����ϰ�, ���� �α��ε� ���̵�(���� ID)�� �Է���id�� ��ġ�ϴ��� üũ
		String user_id = (String)session.getAttribute("user_id"); //���� �α��ε� ���̵�(���ǿ� ����� id)
		if(member != null && user_id.equals(id)) {
			//��й�ȣ ��ġ ���� üũ
			check = member.isCheckedPasswd(passwd);
		}
		
		if(check) {	//���� ����
			//ȸ�� Ż��(ȸ����������)
			dao.deleteMember(member.getMem_num()); //=user_num
			
			//�α׾ƿ�ó�� (���ǹ�ȿȭ)
			session.invalidate();
			
			return "/WEB-INF/views/member/deleteUser.jsp";	//forward ��� ȣ��
		}
		//���� ����
		request.setAttribute("notice_msg", "���̵� �Ǵ� ��й�ȣ�� ����ġ�մϴ�.");
		
		//JSP ��� ��ȯ                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		return "/WEB-INF/views/common/back_singleView.jsp";	//"_singleView.jsp"Ű����� ������ layout.jsp���� include���� �ʰ� �ܵ�ȣ��(������ ������ ������ ��Ģ)
		
	}

}
