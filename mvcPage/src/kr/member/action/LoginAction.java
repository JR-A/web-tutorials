package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		//���۵� ������ ��ȯ
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		//�α���üũ
		MemberVO member = dao.checkMember(id);
		
		//��й�ȣ ����
		boolean check = false;
		if(member!=null) {
			check = member.isCheckedPasswd(passwd);
		}
		
		//check���� true���� false���� üũ
		if(check) {	//���� ����
			//HttpSession ��ȯ
			HttpSession session = request.getSession();
			
			//�α��� ó��(���ǿ� ȸ����ȣ�� ���̵� ����)
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			
			return "redirect:/main/main.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
			
		}
		//���� ����
		request.setAttribute("notice_msg", "���̵� �Ǵ� ��й�ȣ�� ����ġ�մϴ�.");	
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/common/back_singleView.jsp";	//"_singleView.jsp"Ű����� ������ layout.jsp���� include���� �ʰ� �ܵ�ȣ��(������ ������ ������ ��Ģ)
	}

}
