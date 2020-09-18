package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//lunch.html ���� ����

@WebServlet("/todayMenu")
public class LunchServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� Ÿ�� �� ĳ���ͼ� ����
		response.setContentType("text/html;charset=utf-8");
		
		//������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		//���۵� ������ ��ȯ (name�ϳ��� value ���� �� �ִ� ��� - üũ�ڽ� ��) : getParameterValues()�޼��� ���
		String[] selectedMenus = request.getParameterValues("lunch");
		
		//��� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>���� �޴�</title></head>");
		out.println("<body>");
		out.println("<h3>���� ����</h3>");
		
		/*
		  �ƹ��͵� üũ���� �ʰ� ���۹�ư ������ java.lang.NullPointerException �߻�
		 selectedMenus�� null�̹Ƿ� .length ������ �� ���� -> ����üũ �ʿ�
		*/
		if(selectedMenus != null) {	//���� �����Ͱ� �ִ� ���
			for(int i=0; i<selectedMenus.length; i++) {
				out.println(selectedMenus[i] + "<br>");
			}
		}else{	//���� �����Ͱ� ���� ���
			out.println("�������� ����");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//�ڿ� ����
		out.close();
	}
}
