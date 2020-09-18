package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//board_write.html ���� ����

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//������ ���ڵ�
		request.setCharacterEncoding("utf-8");
		
		//���۵� �����͹�ȯ
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>�Խ���</title></head>");
		out.println("<body>");
		out.println("�̸� : " + name + "<br>");
		out.println("���� : " + title + "<br>");
		out.println("���� : " + content);
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ� ����
		out.close();
	}
}
