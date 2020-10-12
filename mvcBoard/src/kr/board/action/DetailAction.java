package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직 처리
		//get방식으로 글 번호 받으므로 인코딩 처리 생략
		int num = Integer.parseInt(request.getParameter("num")); 
		
		//BoardDAO 호출
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(num);	//글 번호로 게시글불러오기
		
		request.setAttribute("boardVO", boardVO);
		
		//JSP 경로 반환
		return "/WEB-INF/views/detail.jsp";
	}

}
