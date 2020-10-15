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
	
	private Map<String,Action> commandMap = new HashMap<String,Action>();//명령어와 명령어 처리 클래스를 쌍으로 저장
  
    //명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 Map객체인 commandMap에 저장
	//명령어와 처리클래스가 매핑되어 있는 properties 파일은 ActionMap.properties파일
	@Override
	public void init(ServletConfig config)throws ServletException{
		// "/WEB-INF/ActionMap.properties" 반환
		String propsPath = config.getInitParameter("propertiesPath");
		
		//web.xml에 아래와 같이 properties 파일 분할할 경우
        // /WEB-INF/ActionMap.properties,/WEB-INF/ActionMap2.properties
        String[] propsArray = propsPath.split(",");
        if(propsArray == null){
        	//properties 파일이 분리되어 있지 않아도 배열로 변환
        	propsArray = (String[])Arrays.asList(propsPath).toArray();
        }
        
        Properties pr = new Properties();//명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
		
        for(String props : propsArray){
        	FileInputStream fis = null;
        	try {
        		String path = config.getServletContext().getRealPath(props);
        		fis = new FileInputStream(path); //ActionMap.properties파일의 내용을 읽어옴
            		pr.load(fis);//ActionMap.properties파일의 정보를  Properties객체에 저장
        	} catch (IOException e) {
            		throw new ServletException(e);
        	} finally {
            		if (fis != null) try { fis.close(); } catch(IOException ex) {}
        	} 	
        }
        System.out.println("-----------------------------");
		//Properties 객체에서 key 구하기
		Iterator<?> keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String)keyIter.next(); //key
			String className = pr.getProperty(command); //value
			
			try {
				//문자열을 이용해 클래스를 찾아 Class 타입으로 반환
				Class<?> commandClass = Class.forName(className);
				//객체로 생성
				Object commandInstance = commandClass.newInstance();
				
				System.out.println(command + "," + commandInstance);
				//HashMap에 key와 value로 등록
				commandMap.put(command, (Action)commandInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			} 
		}
		System.out.println("-----------------------------");
	}

    public void doGet(//get방식의 서비스 메소드
        HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	process(request, response);
    }

    protected void doPost(//post방식의 서비스 메소드
        HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	process(request, response);
    }

    //시용자의 요청을 분석해서 해당 작업을 처리
    private void process(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		String view = null;
        Action action=null;
		try {
            String uri = request.getRequestURI();
            if (uri.indexOf(request.getContextPath()) == 0){
            	uri = uri.substring(request.getContextPath().length());
            }
            
            System.out.println("요청 URI : " + uri);
            
            action = commandMap.get(uri);  
            view = action.execute(request, response);
        } catch(Exception e) {
            throw new ServletException(e);
        }   
		
		if(view.startsWith("redirect:")){//리다이렉트
			view = view.substring("redirect:".length());
			response.sendRedirect(request.getContextPath()+view);
		}else{//포워드
			
			if(!view.endsWith("_singleView.jsp")){
				//==================템플릿 페이지 사용======================//
				request.setAttribute("viewURI", view);
				view = "/WEB-INF/views/template/layout.jsp";
			}
			
			RequestDispatcher dispatcher =request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
		}
    }
}
