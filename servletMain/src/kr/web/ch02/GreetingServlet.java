package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//greeting.html에서 실행

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {
	//public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//데이터 뽑아내기 전에 데이터 인코딩 처리 (post방식의 경우)
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String name = request.getParameter("name");
	
		//출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Greeting</title></head>");
		out.println("<body>");
		out.println(name + "님의 방문을 환영합니다.");
		out.println("</body>");
		out.println("</html>");
		
		//자원 정리
		out.close();
	}
}
