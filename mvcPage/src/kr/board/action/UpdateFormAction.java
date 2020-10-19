package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class UpdateFormAction implements Action {

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
		
		//�α����� ȸ����ȣ�� �ۼ��� ȸ�� ��ȣ ��ġ ���� üũ
		int board_num = Integer.parseInt(request.getParameter("board_num"));	//get������� ���޹��� �۹�ȣ		
		//DAOȣ��
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(board_num);	//�Խñ� ��ȣ�� �Խñ� ��ü ��������
		if(user_num != board.getMem_num()) {	//��ġ���� ������ noticeȭ�� ���
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("board", board); //JSP�� ����
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/board/updateForm.jsp";
	}

}
