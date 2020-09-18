package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now")
public class NowServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 a hh:mm:ss");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>현재 날짜와 시간</title></head>");
		out.println("<body>");
		out.println("현재 날짜와 시간 : " + sf.format(now));  //현재 시간을 우리가 정한 포맷으로 포맷팅
		out.println("</body>");
		out.println("</html>");
		
		//출력 스트림 자원정리
		out.close();
	}
}
