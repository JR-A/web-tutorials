package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

//�� Ŭ����(���� ó���ϰ� JSP ��� ��ȯ)
public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���� ó��
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> list = dao.getList();	//�� ��� �ҷ�����
		
		request.setAttribute("list", list);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/list.jsp";
	}

}
