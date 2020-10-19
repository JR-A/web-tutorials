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

		//����ó��
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//�α��� �� ȣ��
			return "redirect:/member/loginForm.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
		}
		
		//���۵� ������ ó�� �� ���� ���ε�
		//���ε�� ���ϰ� �� �� �����͸� ó���ϱ� ���� MultipartRequest ��ü ����
		MultipartRequest multi = FileUtil.createFile(request);
		//post������� ���۹޾�����, MultipartRequest ��ü �����ϸ鼭 ���ڵ�Ÿ�� �����ϹǷ� ���ڵ� ó�� ���ʿ�
		//MultipartRequest ��ü �����ϸ鼭 �̹� ���ε���(.metadata �������� ������)�� ������ ���ε� �Ǿ���

		int board_num = Integer.parseInt(multi.getParameter("board_num"));	
		String filename = multi.getFilesystemName("filename");
		
		//DAOȣ��
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO dbBoard = dao.getBoard(board_num);	//���� �� ������
		
		//�α����� ȸ����ȣ�� �ۼ��� ȸ�� ��ȣ ��ġ ���� üũ
		if(user_num != dbBoard.getMem_num()) {
			//���ε�� ���� ����
			if(filename != null) {
				FileUtil.removeFile(filename);	//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ���� �ʾƵ� MultipartRequest�����ϸ� ������ ���ε� �Ǿ����Ƿ� ���ε�� ������ ����
			}
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//�ڹٺ� ����
		BoardVO board = new BoardVO();
		board.setBoard_num(board_num);
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFilename(filename);
		board.setIp(request.getRemoteAddr()); //ip�ּҴ� request���� �޾ƿ���
		board.setMem_num(user_num);	 //���ǿ��� ���� ȸ����ȣ

		dao.updateBoard(board);		//�� ����
		
		//�� ������ ���ε� ���� ��� ���� ������ ����(���� �����̵�, �ƴϵ� MultipartRequest�����ϸ� ������ ���ε� �Ǿ����Ƿ�)
		//filename�� null�̸� ���� ������ �״�� ����ϴ� ���
		if(filename != null) {
			FileUtil.removeFile(dbBoard.getFilename());
		}
		
		//JSP ��� ��ȯ
		//return "/WEB-INF/views/board/update.jsp";
		
		//������� redirect (update.jsp ������ ������ �ʾƵ� ��)
		return "redirect:/board/list.do";
	}

}
