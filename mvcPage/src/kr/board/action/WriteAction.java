package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action {

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
		
		//업로드된 파일과 그 외 데이터를 처리하기 위해 MultipartRequest 객체 생성
		MultipartRequest multi = FileUtil.createFile(request);
		//post방식으로 전송받았지만, MultipartRequest 객체 생성하면서 인코딩타입 지정하므로 인코딩 처리 불필요
		//MultipartRequest 객체 생성하면서 이미 업로드경로(.metadata 폴더내의 절대경로)에 파일이 업로드 되었음
		
		//자바빈 생성
		BoardVO board = new BoardVO();
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFilename(multi.getFilesystemName("filename"));	//getParameter로 받아오지 않음
		board.setIp(request.getRemoteAddr()); //ip주소는 request에서 받아오기
		board.setMem_num(user_num);	 //세션에서 얻어온 회원번호
		
		//DAO호출
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.insertBoard(board);  //게시글 등록
	
		//JSP 경로 반환
		return "/WEB-INF/views/board/write.jsp";
	}

}
