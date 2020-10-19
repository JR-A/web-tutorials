package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���� ó��
		//�� ��ȣ ��ȯ
		int board_num = Integer.parseInt(request.getParameter("board_num"));	//get����̹Ƿ� ���۵� ������ ���ڵ� ó�� ���ʿ�
		
		//DAOȣ��
		BoardDAO dao = BoardDAO.getInstance();
		
		//��ȸ�� ����(DB�� ��ȸ�� �ݿ�)
		dao.updateReadCount(board_num);
		
		//�� ��ȣ�� �ش��ϴ� ���ڵ�(������, �� ��ü) ��ȯ
		BoardVO board = dao.getBoard(board_num);	//getBoard�� �Խñ� ���������� ����ϴ� �޼����̹Ƿ� ��ü ������ �� ����

		//���� - HTML �±� ����, �ٹٲ� ����
		board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//���� - HTML �±� ����, �ٹٲ� ���
		board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		
		request.setAttribute("board", board); //jsp�� ����
		
		//JPS ��� ��ȯ
		return "/WEB-INF/views/board/detail.jsp";
	}

}
