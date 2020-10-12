package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		//BoardDAO 호출
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(num);
		boolean check = false;
		
		//비밀번호 일치 여부 체크
		if(board!=null) {
			check = board.isCheckedPasswd(passwd);
		}
		
		if(check) {	//전송된 비밀번호와 테이블로부터 읽어들인 비밀번호가 일치
			//글 삭제
			dao.delete(num);
		}
		
		request.setAttribute("check", check);
	
		//JSP 경로 반환
		return "/WEB-INF/views/delete.jsp";
	}

}
