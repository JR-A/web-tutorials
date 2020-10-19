package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class UpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인 폼 호출
			return "redirect:/member/loginForm.do";	//'redirect:'키워드로 시작하면 리다이렉트 방식으로 페이지호출(서블릿에 정의한 임의의 규칙)
		}
		
		//로그인한 회원번호와 작성자 회원 번호 일치 여부 체크
		int board_num = Integer.parseInt(request.getParameter("board_num"));	//get방식으로 전달받은 글번호		
		//DAO호출
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(board_num);	//게시글 번호로 게시글 객체 가져오기
		if(user_num != board.getMem_num()) {	//일치하지 않으면 notice화면 출력
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("board", board); //JSP와 공유
		
		//JSP 경로 반환
		return "/WEB-INF/views/board/updateForm.jsp";
	}

}
