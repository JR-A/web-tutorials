package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���� ó��
		//get������� �� ��ȣ �����Ƿ� ���ڵ� ó�� ����
		int num = Integer.parseInt(request.getParameter("num")); 
		
		//BoardDAO ȣ��
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(num);	//�� ��ȣ�� �Խñۺҷ�����
		
		request.setAttribute("boardVO", boardVO);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/detail.jsp";
	}

}
