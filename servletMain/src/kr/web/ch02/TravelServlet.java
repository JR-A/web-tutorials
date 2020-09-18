package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//travel.html ���� ����

@WebServlet("/travel")
public class TravelServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *	[�ǽ�] �̸��� �Է��ϰ� �������� �����ؼ� �����ϸ�
		 *   ���۵� �̸��� �������� ���
		 *  
		 *   ��¿��� )
		 *   	�̸� : ȫ�浿
		 *   	[������]
		 *   	����
		 *   	�ĸ�
		 */
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ
		String name = request.getParameter("name");
		String[] cities = request.getParameterValues("city");
		
		//��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>����</title></head>");
		out.println("<body>");
		out.println("�̸� : " + name + "<br>" );
		out.println("[������]<br>");
		
		if(cities != null) {
			out.println("<ul>");
			for(int i=0; i<cities.length; i++) {
				out.println( "<li>" + cities[i] + "</li>");
			}
			out.println("</ul>");
		}else {
			out.println("���õ� �������� �����ϴ�.");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ�����
		out.close();
	}
}
