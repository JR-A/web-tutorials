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
		
		//로직 처리
		//글 번호 반환
		int board_num = Integer.parseInt(request.getParameter("board_num"));	//get방식이므로 전송된 데이터 인코딩 처리 불필요
		
		//DAO호출
		BoardDAO dao = BoardDAO.getInstance();
		
		//조회수 증가(DB에 조회수 반영)
		dao.updateReadCount(board_num);
		
		//글 번호에 해당하는 레코드(데이터, 글 객체) 반환
		BoardVO board = dao.getBoard(board_num);	//getBoard는 게시글 수정에서도 사용하는 메서드이므로 객체 가져온 후 수정

		//제목 - HTML 태그 불허, 줄바꿈 불허
		board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//내용 - HTML 태그 불허, 줄바꿈 허용
		board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		
		request.setAttribute("board", board); //jsp에 공유
		
		//JPS 경로 반환
		return "/WEB-INF/views/board/detail.jsp";
	}

}
