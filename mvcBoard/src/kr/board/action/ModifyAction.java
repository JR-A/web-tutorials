package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class ModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//����ó��
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ, �ڹٺ��� �����ؼ� �ڹٺ��� ������Ƽ�� ������ ����
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());		//ip�� ����� �Է��� �ƴ϶� ����� ���� ���޹���
		
		//BoardDAO ȣ��
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(boardVO.getNum());
		boolean check = false;
		
		//��й�ȣ ��ġ ���� üũ
		if(board!=null) {
			check = board.isCheckedPasswd(boardVO.getPasswd());
		}
		
		if(check) {	//���۵� ��й�ȣ�� ���̺�κ��� �о���� ��й�ȣ�� ��ġ
			//�� ����
			dao.update(boardVO);
		}
		
		request.setAttribute("check", check);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/modify.jsp";
	}

}
