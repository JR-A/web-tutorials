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
		
		//�˻� �׸�
		String keyfield = request.getParameter("keyfield"); //�޺��ڽ� �ɼ�(����, �ۼ���, ����) �� 1
		//�˻���
		String keyword = request.getParameter("keyword");
		//null�̸� �� ���ڿ��� ġȯ
		if(keyfield == null) keyfield = "";
		if(keyword == null) keyword = "";
		
		//����¡ ó�� ���� ���� �����ִ� ������ ��ȣ(pageNum) ���۹���
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";	//�Խ����� ó�� ȣ���� �ÿ� list.do�θ� ȣ���ϹǷ� get������� pageNum�Ѿ���� ���� -> pageNum�� Null�϶��� ù �������� ����
		
		//DAOȣ��
		BoardDAO dao = BoardDAO.getInstance();
		
		int count = dao.getBoardCount(keyfield, keyword);	//��ü �� ����
		
		//����¡ó��
		//PagingUtil(String keyfield, String keyword, int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "list.do");
		
		List<BoardVO> list = null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartCount(), page.getEndCount(), keyfield, keyword);	//��� �ҷ�����
		}
		request.setAttribute("count", count);						//��ü �� ����
		request.setAttribute("list", list);							//�� ���
		request.setAttribute("pagingHtml", page.getPagingHtml()); 	//������ ǥ�� ���ڿ�
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/board/list.jsp";
	}

}
