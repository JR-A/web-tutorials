package kr.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//여기서 실행
/*
 get방식으로 테스트
http://localhost:8080/mvcMain/*.do 에서
 *.do -> list.do
 *.do -> detail.do 
*/

public class DispatcherServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		requestPro(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		
		
		Action com = null;
		String view = null;
		
		String command = request.getRequestURI();
		System.out.println("command 1 : " + command);
		
		//					'/mvcMain'('/프로젝트명')이 맨 앞에 있는 경우
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());
		}
		System.out.println("command 2 : " + command);
		
		
		if(command == null || command.equals("/list.do")) {
			com = new ListAction();
		}else if(command.equals("/detail.do")) {
			com = new DetailAction();
		}else if(command.equals("/write.do")) {
			com = new WriteAction();
		}
		
		//모델 클래스의 메서드 호출
		try {
			view = com.execute(request, response);	//request, response공유 후 JSP경로 반환받음
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//forward 방식으로 View(JSP) 호출함. 서블릿 호출시 만들어진 request, response를 JSP와 공유함
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);	//request와 response를 공유. 제어를 넘김. messageView.jsp의 결과가 출력됨
		
	}
}
