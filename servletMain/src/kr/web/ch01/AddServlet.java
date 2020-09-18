package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//add.html에서 실행

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *	views 폴더에 add.html을 저장 후
		 *  정수 2개를 입력받아 서블릿에 전송
		 *  전송된 정수 2개를 반환받아 덧셈하고 출력
		 *   
		 *  출력예시)
		 *  3 + 4 = 7
		 */
		
		//입력된 정수 받고 연산하기
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		//int result = num1 + num2;
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>덧셈</title></head>");
		out.println("<body>");
		out.println("<h2>");
		//out.println(num1 + " + " + num2 + " = " + result);
		out.println(num1 + " + " + num2 + " = " + (num1+num2));
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
		
		//자원정리
		out.close();
	}
}
