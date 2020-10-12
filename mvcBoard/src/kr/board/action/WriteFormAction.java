package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

//모델 클래스(로직 처리하고 JSP 경로 반환)
public class WriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직 처리
		
		//JSP 경로 반환
		return "/WEB-INF/views/writeForm.jsp";
	}

}
