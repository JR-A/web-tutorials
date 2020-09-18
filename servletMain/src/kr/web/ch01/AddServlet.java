package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//add.html���� ����

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *	views ������ add.html�� ���� ��
		 *  ���� 2���� �Է¹޾� ������ ����
		 *  ���۵� ���� 2���� ��ȯ�޾� �����ϰ� ���
		 *   
		 *  ��¿���)
		 *  3 + 4 = 7
		 */
		
		//�Էµ� ���� �ް� �����ϱ�
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		//int result = num1 + num2;
		
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>����</title></head>");
		out.println("<body>");
		out.println("<h2>");
		//out.println(num1 + " + " + num2 + " = " + result);
		out.println(num1 + " + " + num2 + " = " + (num1+num2));
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ�����
		out.close();
	}
}
