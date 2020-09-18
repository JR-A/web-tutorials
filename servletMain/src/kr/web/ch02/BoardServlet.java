package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//board_write.html 에서 실행

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//데이터 인코딩
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터반환
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>게시판</title></head>");
		out.println("<body>");
		out.println("이름 : " + name + "<br>");
		out.println("제목 : " + title + "<br>");
		out.println("내용 : " + content);
		out.println("</body>");
		out.println("</html>");
		
		//자원 정리
		out.close();
	}
}
