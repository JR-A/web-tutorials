package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//greeting.html���� ����

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {
	//public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//������ �̾Ƴ��� ���� ������ ���ڵ� ó�� (post����� ���)
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ
		String name = request.getParameter("name");
	
		//��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Greeting</title></head>");
		out.println("<body>");
		out.println(name + "���� �湮�� ȯ���մϴ�.");
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ� ����
		out.close();
	}
}
