package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckIdAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* http://localhost:8080/mvcPage/member/checkId.do?id=kim (get방식. ajax제대로 생성되어 전달되는지 테스트) */
		
		/*
		 * 로직 처리
		 */
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		//DAO호출
		MemberDAO dao = MemberDAO.getInstance();
		//아이디 중복 체크
		MemberVO member = dao.checkMember(id);
		
		/*
		 * Jackson 라이브러리 이용하여 JSON 문자열 생성하기
		 */
		//HashMap 객체 생성하여 key-value 쌍으로 값 저장
		Map<String, String> mapAjax = new HashMap<String, String>();
		if(member == null) {	//아이디 미중복
			mapAjax.put("result", "idNotFound");
		}else {					//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}
		
		/*
		 *	JSON 형식으로 변환하기를 원하는 문자열을 HashMap에 key-value 쌍으로 저장 후
		 *	ObjectMapper의 writeValueAsString 메서드에 Map객체를 전달하여 '일반 문자열 데이터' -> 'JSON형식의 문자열 데이터'로 변환 후 반환 
		 */
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_singleView.jsp"; //"_singleView.jsp"키워드로 끝나면 layout.jsp에서 include하지 않고 단독호출(서블릿에 정의한 임의의 규칙)
	}

}
