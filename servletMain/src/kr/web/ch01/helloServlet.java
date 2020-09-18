package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//������ HttpServlet�� ����ؾ� ��� ����
public class HelloServlet extends HttpServlet{
	//Ŭ���̾�Ʈ(��������)�� ��û�ϸ� HelloServlet ��ü�� �����ǰ� doGet �޼��� ȣ���
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//HTML ����� ���� ��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>First Servlet</title></head>");
		out.println("<body>");
		out.println("ó�� �ۼ��ϴ� servlet�Դϴ�.");
		out.println("</body>");
		out.println("</html>");
		
		//��� ��Ʈ�� �ڿ�����
		out.close();
	}
}
