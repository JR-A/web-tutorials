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
		 * [�ǽ�] 1���� 100������ �� ���
		 * 
		 * ��� ����) body�±׿� �Է�	
		 * 1���� 100������ ���� 5050
		 *  
		 */
		
		//1���� 100������ �� ���ϱ�
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum += i;
		}
		
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//HTML ��� ���� ��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<header><title>1���� 100������ ��</title></header>");
		out.println("<body>");
		out.println("1���� 100������ ���� " + sum);
		out.println("</body>");
		out.println("</html>");
		
		//��� ��Ʈ�� �ڿ� ����
		out.close();
	}
}
