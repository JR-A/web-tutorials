package kr.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//���⼭ ����
/*
 get������� �׽�Ʈ
http://localhost:8080/mvcMain/*.do ����
 *.do -> list.do
 *.do -> detail.do 
*/

public class DispatcherServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		requestPro(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		
		
		Action com = null;
		String view = null;
		
		String command = request.getRequestURI();
		System.out.println("command 1 : " + command);
		
		//					'/mvcMain'('/������Ʈ��')�� �� �տ� �ִ� ���
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());
		}
		System.out.println("command 2 : " + command);
		
		
		if(command == null || command.equals("/list.do")) {
			com = new ListAction();
		}else if(command.equals("/detail.do")) {
			com = new DetailAction();
		}else if(command.equals("/write.do")) {
			com = new WriteAction();
		}
		
		//�� Ŭ������ �޼��� ȣ��
		try {
			view = com.execute(request, response);	//request, response���� �� JSP��� ��ȯ����
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//forward ������� View(JSP) ȣ����. ���� ȣ��� ������� request, response�� JSP�� ������
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);	//request�� response�� ����. ��� �ѱ�. messageView.jsp�� ����� ��µ�
		
	}
}
