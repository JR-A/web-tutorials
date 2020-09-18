package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//lunch.html 에서 실행

@WebServlet("/todayMenu")
public class LunchServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환 (name하나에 value 여러 개 있는 경우 - 체크박스 등) : getParameterValues()메서드 사용
		String[] selectedMenus = request.getParameterValues("lunch");
		
		//출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>점심 메뉴</title></head>");
		out.println("<body>");
		out.println("<h3>오늘 점심</h3>");
		
		/*
		  아무것도 체크하지 않고 전송버튼 누르면 java.lang.NullPointerException 발생
		 selectedMenus가 null이므로 .length 접근할 수 없음 -> 조건체크 필요
		*/
		if(selectedMenus != null) {	//전송 데이터가 있는 경우
			for(int i=0; i<selectedMenus.length; i++) {
				out.println(selectedMenus[i] + "<br>");
			}
		}else{	//전송 데이터가 없는 경우
			out.println("선택하지 않음");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		//자원 정리
		out.close();
	}
}
