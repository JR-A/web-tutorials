package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 HttpServlet을 상속해야 사용 가능
public class HelloServlet extends HttpServlet{
	//클라이언트(웹브라우저)가 요청하면 HelloServlet 객체사 생성되고 doGet 메서드 호출됨
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>First Servlet</title></head>");
		out.println("<body>");
		out.println("처음 작성하는 servlet입니다.");
		out.println("</body>");
		out.println("</html>");
		
		//출력 스트림 자원정리
		out.close();
	}
}
