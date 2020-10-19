package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action {

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

		//JSP 경로 반환
		return "/WEB-INF/views/board/writeForm.jsp";
	}

}
