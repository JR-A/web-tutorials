package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action {

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
		
		//전송된 데이터 처리 및 파일 업로드
		//업로드된 파일과 그 외 데이터를 처리하기 위해 MultipartRequest 객체 생성
		MultipartRequest multi = FileUtil.createFile(request);
		//post방식으로 전송받았지만, MultipartRequest 객체 생성하면서 인코딩타입 지정하므로 인코딩 처리 불필요
		//MultipartRequest 객체 생성하면서 이미 업로드경로(.metadata 폴더내의 절대경로)에 파일이 업로드 되었음

		int board_num = Integer.parseInt(multi.getParameter("board_num"));	
		String filename = multi.getFilesystemName("filename");
		
		//DAO호출
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO dbBoard = dao.getBoard(board_num);	//수정 전 데이터
		
		//로그인한 회원번호와 작성자 회원 번호 일치 여부 체크
		if(user_num != dbBoard.getMem_num()) {
			//업로드된 파일 삭제
			if(filename != null) {
				FileUtil.removeFile(filename);	//로그인한 회원번호와 작성자 회원번호가 일치하지 않아도 MultipartRequest생성하며 파일이 업로드 되었으므로 업로드된 파일을 삭제
			}
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//자바빈 생성
		BoardVO board = new BoardVO();
		board.setBoard_num(board_num);
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFilename(filename);
		board.setIp(request.getRemoteAddr()); //ip주소는 request에서 받아오기
		board.setMem_num(user_num);	 //세션에서 얻어온 회원번호

		dao.updateBoard(board);		//글 수정
		
		//새 파일을 업로드 했을 경우 예전 파일을 삭제(같은 파일이든, 아니든 MultipartRequest생성하며 파일이 업로드 되었으므로)
		//filename이 null이면 기존 파일을 그대로 사용하는 경우
		if(filename != null) {
			FileUtil.removeFile(dbBoard.getFilename());
		}
		
		//JSP 경로 반환
		//return "/WEB-INF/views/board/update.jsp";
		
		//목록으로 redirect (update.jsp 파일을 만들지 않아도 됨)
		return "redirect:/board/list.do";
	}

}
