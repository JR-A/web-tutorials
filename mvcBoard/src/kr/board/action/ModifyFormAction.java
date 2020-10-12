package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리(DetailAction.java와 동일)
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(Integer.parseInt(request.getParameter("num")));
		
		request.setAttribute("boardVO", boardVO);
		
		//JSP 경로 반환
		return "/WEB-INF/views/modifyForm.jsp";
	}

}
