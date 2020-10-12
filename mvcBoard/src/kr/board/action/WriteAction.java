package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ, �ڹٺ��� �����ؼ� �ڹٺ��� ������Ƽ�� ������ ����
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());		//ip�� ����� �Է��� �ƴ϶� ����� ���� ���޹���
		
		//BoardDAO ȣ��
		BoardDAO dao = BoardDAO.getInstance();
		//�� ����
		dao.insert(boardVO);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/write.jsp";
	}

}
