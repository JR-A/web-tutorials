package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

//모델 클래스(로직 처리하고 JSP 경로 반환)
public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직 처리
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> list = dao.getList();	//글 목록 불러오기
		
		request.setAttribute("list", list);
		
		//JSP 경로 반환
		return "/WEB-INF/views/list.jsp";
	}

}
