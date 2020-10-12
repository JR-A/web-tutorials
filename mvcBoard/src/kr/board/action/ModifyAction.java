package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class ModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환, 자바빈을 생성해서 자바빈의 프로퍼티에 데이터 저장
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());		//ip는 사용자 입력이 아니라 헤더를 통해 전달받음
		
		//BoardDAO 호출
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(boardVO.getNum());
		boolean check = false;
		
		//비밀번호 일치 여부 체크
		if(board!=null) {
			check = board.isCheckedPasswd(boardVO.getPasswd());
		}
		
		if(check) {	//전송된 비밀번호와 테이블로부터 읽어들인 비밀번호가 일치
			//글 수정
			dao.update(boardVO);
		}
		
		request.setAttribute("check", check);
		
		//JSP 경로 반환
		return "/WEB-INF/views/modify.jsp";
	}

}
