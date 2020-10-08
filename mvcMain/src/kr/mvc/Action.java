package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//여러 Model 클래스를 하나의 통합된 데이터타입으로 제어하기 위해 인터페이스 정의
//Servlet에서 코드를 자주 고치지 않기위해
public interface Action {
	//추상메서드(구현부 없음. 인터페이스 implement하는 클래스에서 구현하도록 강요)
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
}
