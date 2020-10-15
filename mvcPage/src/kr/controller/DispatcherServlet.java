package kr.controller;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
     
public class DispatcherServlet extends HttpServlet {
      
	private static final long serialVersionUID = -58006169049053752L;
	
	private Map<String,Action> commandMap = new HashMap<String,Action>();//��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
  
    //��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ �о Map��ü�� commandMap�� ����
	//��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ ActionMap.properties����
	@Override
	public void init(ServletConfig config)throws ServletException{
		// "/WEB-INF/ActionMap.properties" ��ȯ
		String propsPath = config.getInitParameter("propertiesPath");
		
		//web.xml�� �Ʒ��� ���� properties ���� ������ ���
        // /WEB-INF/ActionMap.properties,/WEB-INF/ActionMap2.properties
        String[] propsArray = propsPath.split(",");
        if(propsArray == null){
        	//properties ������ �и��Ǿ� ���� �ʾƵ� �迭�� ��ȯ
        	propsArray = (String[])Arrays.asList(propsPath).toArray();
        }
        
        Properties pr = new Properties();//��ɾ�� ó��Ŭ������ ���������� ������ Properties��ü ����
		
        for(String props : propsArray){
        	FileInputStream fis = null;
        	try {
        		String path = config.getServletContext().getRealPath(props);
        		fis = new FileInputStream(path); //ActionMap.properties������ ������ �о��
            		pr.load(fis);//ActionMap.properties������ ������  Properties��ü�� ����
        	} catch (IOException e) {
            		throw new ServletException(e);
        	} finally {
            		if (fis != null) try { fis.close(); } catch(IOException ex) {}
        	} 	
        }
        System.out.println("-----------------------------");
		//Properties ��ü���� key ���ϱ�
		Iterator<?> keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String)keyIter.next(); //key
			String className = pr.getProperty(command); //value
			
			try {
				//���ڿ��� �̿��� Ŭ������ ã�� Class Ÿ������ ��ȯ
				Class<?> commandClass = Class.forName(className);
				//��ü�� ����
				Object commandInstance = commandClass.newInstance();
				
				System.out.println(command + "," + commandInstance);
				//HashMap�� key�� value�� ���
				commandMap.put(command, (Action)commandInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			} 
		}
		System.out.println("-----------------------------");
	}

    public void doGet(//get����� ���� �޼ҵ�
        HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	process(request, response);
    }

    protected void doPost(//post����� ���� �޼ҵ�
        HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	process(request, response);
    }

    //�ÿ����� ��û�� �м��ؼ� �ش� �۾��� ó��
    private void process(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		String view = null;
        Action action=null;
		try {
            String uri = request.getRequestURI();
            if (uri.indexOf(request.getContextPath()) == 0){
            	uri = uri.substring(request.getContextPath().length());
            }
            
            System.out.println("��û URI : " + uri);
            
            action = commandMap.get(uri);  
            view = action.execute(request, response);
        } catch(Exception e) {
            throw new ServletException(e);
        }   
		
		if(view.startsWith("redirect:")){//�����̷�Ʈ
			view = view.substring("redirect:".length());
			response.sendRedirect(request.getContextPath()+view);
		}else{//������
			
			if(!view.endsWith("_singleView.jsp")){
				//==================���ø� ������ ���======================//
				request.setAttribute("viewURI", view);
				view = "/WEB-INF/views/template/layout.jsp";
			}
			
			RequestDispatcher dispatcher =request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
		}
    }
}
