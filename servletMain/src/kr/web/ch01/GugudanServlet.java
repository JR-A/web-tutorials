package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//gugu.html���� �����ϱ�

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//form���� ���۵� ������ �ޱ� (key-value��). ���۹��� ���� ��ȯ
		//		  String -> int
		int dan = Integer.parseInt(request.getParameter("dan")); //input�±��� name�Ӽ��� key�� value���. String���̹Ƿ� int�� ����ȯ�ʿ�
		
		//HTML ����� ���� ��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>������</title></head>");
		out.println("<body>");
		out.println( "=== " + dan + " ��===<br>");
		
		for(int i=1; i<10; i++) {
			out.println(dan + " * " + i + " = " + dan*i + "<br>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ�����
		out.close();
	}
}
