package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hundred")
public class HundredServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * [실습] 1부터 100까지의 합 출력
		 * 
		 * 출력 예시) body태그에 입력	
		 * 1부터 100까지의 합은 5050
		 *  
		 */
		
		//1부터 100까지의 합 구하기
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum += i;
		}
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<header><title>1부터 100까지의 합</title></header>");
		out.println("<body>");
		out.println("1부터 100까지의 합은 " + sum);
		out.println("</body>");
		out.println("</html>");
		
		//출력 스트림 자원 정리
		out.close();
	}
}
