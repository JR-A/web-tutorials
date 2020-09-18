package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//gugu.html에서 실행하기

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//form에서 전송된 데이터 받기 (key-value쌍). 전송받은 단을 반환
		//		  String -> int
		int dan = Integer.parseInt(request.getParameter("dan")); //input태그의 name속성이 key인 value얻기. String형이므로 int로 형변환필요
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>구구단</title></head>");
		out.println("<body>");
		out.println( "=== " + dan + " 단===<br>");
		
		for(int i=1; i<10; i++) {
			out.println(dan + " * " + i + " = " + dan*i + "<br>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//자원정리
		out.close();
	}
}
