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

		//����ó��
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//�α��� �� ȣ��
			return "redirect:/member/loginForm.do";	//'redirect:'Ű����� �����ϸ� �����̷�Ʈ ������� ������ȣ��(������ ������ ������ ��Ģ)
		}
		
		//���ε�� ���ϰ� �� �� �����͸� ó���ϱ� ���� MultipartRequest ��ü ����
		MultipartRequest multi = FileUtil.createFile(request);
		//post������� ���۹޾�����, MultipartRequest ��ü �����ϸ鼭 ���ڵ�Ÿ�� �����ϹǷ� ���ڵ� ó�� ���ʿ�
		//MultipartRequest ��ü �����ϸ鼭 �̹� ���ε���(.metadata �������� ������)�� ������ ���ε� �Ǿ���
		
		//�ڹٺ� ����
		BoardVO board = new BoardVO();
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFilename(multi.getFilesystemName("filename"));	//getParameter�� �޾ƿ��� ����
		board.setIp(request.getRemoteAddr()); //ip�ּҴ� request���� �޾ƿ���
		board.setMem_num(user_num);	 //���ǿ��� ���� ȸ����ȣ
		
		//DAOȣ��
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.insertBoard(board);  //�Խñ� ���
	
		//JSP ��� ��ȯ
		return "/WEB-INF/views/board/write.jsp";
	}

}
