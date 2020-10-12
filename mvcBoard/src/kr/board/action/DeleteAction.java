package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		//BoardDAO ȣ��
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(num);
		boolean check = false;
		
		//��й�ȣ ��ġ ���� üũ
		if(board!=null) {
			check = board.isCheckedPasswd(passwd);
		}
		
		if(check) {	//���۵� ��й�ȣ�� ���̺�κ��� �о���� ��й�ȣ�� ��ġ
			//�� ����
			dao.delete(num);
		}
		
		request.setAttribute("check", check);
	
		//JSP ��� ��ȯ
		return "/WEB-INF/views/delete.jsp";
	}

}
