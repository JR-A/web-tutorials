package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//travel.html 에서 실행

@WebServlet("/travel")
public class TravelServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *	[실습] 이름을 입력하고 여행지를 선택해서 전송하면
		 *   전송된 이름과 여행지를 출력
		 *  
		 *   출력예시 )
		 *   	이름 : 홍길동
		 *   	[여행지]
		 *   	런던
		 *   	파리
		 */
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String name = request.getParameter("name");
		String[] cities = request.getParameterValues("city");
		
		//출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>여행</title></head>");
		out.println("<body>");
		out.println("이름 : " + name + "<br>" );
		out.println("[여행지]<br>");
		
		if(cities != null) {
			out.println("<ul>");
			for(int i=0; i<cities.length; i++) {
				out.println( "<li>" + cities[i] + "</li>");
			}
			out.println("</ul>");
		}else {
			out.println("선택된 여행지가 없습니다.");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//자원정리
		out.close();
	}
}
