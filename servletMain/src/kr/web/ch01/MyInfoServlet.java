package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myInfo")
public class MyInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * [�ǽ�] ���� ���� ����ϱ�
		 * (�����͸� ������ ������ ���� - name, age, job, address)
		 * ������ ������ ���� ������ �� body���� �Ʒ��� ���� ���� ȣ��
		 * 
		 * ��¿��� )
		 * �̸� : ȫ�浿
		 * ���� : 20��
		 * ���� : �л�
		 * �ּ� : �����
		 * 
		 */
		String name = "ȫ�浿";
		int age = 20;
		String job = "�л�";
		String address = "�����";
		
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//HTML ��� ���� ��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<header><title>���� ���� ���</title></header>");
		out.println("<body>");
		out.println("�̸� : " + name + "<br>");
		out.println("���� : " + age + "��<br>");
		out.println("���� : " + job + "<br>");
		out.println("�ּ� : " + address);
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ�����
		out.close();
	}
}
