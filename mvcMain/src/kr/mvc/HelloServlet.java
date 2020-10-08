package kr.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")    /* /�� context ��� */
public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//HTML ����� ���� ��� ��Ʈ���� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Hello Servlet</title></head>");
		out.println("<body>");
		out.println("������ ���������� �����մϴ�.");
		out.println("</body>");
		out.println("</html>");
		
		//��� ��Ʈ�� �ڿ�����
		out.close();
		
	}
}
