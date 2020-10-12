package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환, 자바빈을 생성해서 자바빈의 프로퍼티에 데이터 저장
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());		//ip는 사용자 입력이 아니라 헤더를 통해 전달받음
		
		//BoardDAO 호출
		BoardDAO dao = BoardDAO.getInstance();
		//글 저장
		dao.insert(boardVO);
		
		//JSP 경로 반환
		return "/WEB-INF/views/write.jsp";
	}

}
