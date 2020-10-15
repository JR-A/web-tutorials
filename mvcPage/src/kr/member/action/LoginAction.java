package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로직처리
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//DAO호출
		MemberDAO dao = MemberDAO.getInstance();
		//로그인체크
		MemberVO member = dao.checkMember(id);
		
		//비밀번호 인증
		boolean check = false;
		if(member!=null) {
			check = member.isCheckedPasswd(passwd);
		}
		
		//check값이 true인지 false인지 체크
		if(check) {	//인증 성공
			//HttpSession 반환
			HttpSession session = request.getSession();
			
			//로그인 처리(세션에 회원번호와 아이디 저장)
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			
			return "redirect:/main/main.do";	//'redirect:'키워드로 시작하면 리다이렉트 방식으로 페이지호출(서블릿에 정의한 임의의 규칙)
			
		}
		//인증 실패
		request.setAttribute("notice_msg", "아이디 또는 비밀번호가 불일치합니다.");	
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/back_singleView.jsp";	//"_singleView.jsp"키워드로 끝나면 layout.jsp에서 include하지 않고 단독호출(서블릿에 정의한 임의의 규칙)
	}

}
