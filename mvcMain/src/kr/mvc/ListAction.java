package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements Action {	//Action인터페이스의 추상메서드를 반드시 구현해야함

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request에 데이터 저장
		request.setAttribute("message", "게시판 목록입니다.");
		
		//JSP 경로 반환
		return "/views/list.jsp";
	}

}
