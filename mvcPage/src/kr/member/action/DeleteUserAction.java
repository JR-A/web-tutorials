package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class DeleteUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//로직처리
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인 폼 호출
			return "redirect:/member/loginForm.do";	//'redirect:'키워드로 시작하면 리다이렉트 방식으로 페이지호출(서블릿에 정의한 임의의 규칙)
		}
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//DAO호출
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		//입력한id에 해당하는 회원정보가 존재하고, 현재 로그인된 아이디(세션 ID)와 입력한id가 일치하는지 체크
		String user_id = (String)session.getAttribute("user_id"); //현재 로그인된 아이디(세션에 저장된 id)
		if(member != null && user_id.equals(id)) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPasswd(passwd);
		}
		
		if(check) {	//인증 성공
			//회원 탈퇴(회원정보삭제)
			dao.deleteMember(member.getMem_num()); //=user_num
			
			//로그아웃처리 (세션무효화)
			session.invalidate();
			
			return "/WEB-INF/views/member/deleteUser.jsp";	//forward 방식 호출
		}
		//인증 실패
		request.setAttribute("notice_msg", "아이디 또는 비밀번호가 불일치합니다.");
		
		//JSP 경로 반환                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		return "/WEB-INF/views/common/back_singleView.jsp";	//"_singleView.jsp"키워드로 끝나면 layout.jsp에서 include하지 않고 단독호출(서블릿에 정의한 임의의 규칙)
		
	}

}
