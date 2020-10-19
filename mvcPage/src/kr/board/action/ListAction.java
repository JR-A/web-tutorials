package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//검색 항목
		String keyfield = request.getParameter("keyfield"); //콤보박스 옵션(제목, 작성자, 내용) 중 1
		//검색어
		String keyword = request.getParameter("keyword");
		//null이면 빈 문자열로 치환
		if(keyfield == null) keyfield = "";
		if(keyword == null) keyword = "";
		
		//페이징 처리 위해 현재 보고있는 페이지 번호(pageNum) 전송받음
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";	//게시판을 처음 호출할 시에 list.do로만 호출하므로 get방식으로 pageNum넘어오지 않음 -> pageNum이 Null일때는 첫 페이지로 간주
		
		//DAO호출
		BoardDAO dao = BoardDAO.getInstance();
		
		int count = dao.getBoardCount(keyfield, keyword);	//전체 글 개수
		
		//페이징처리
		//PagingUtil(String keyfield, String keyword, int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "list.do");
		
		List<BoardVO> list = null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartCount(), page.getEndCount(), keyfield, keyword);	//목록 불러오기
		}
		request.setAttribute("count", count);						//전체 글 개수
		request.setAttribute("list", list);							//글 목록
		request.setAttribute("pagingHtml", page.getPagingHtml()); 	//페이지 표시 문자열
		
		//JSP 경로 반환
		return "/WEB-INF/views/board/list.jsp";
	}

}
