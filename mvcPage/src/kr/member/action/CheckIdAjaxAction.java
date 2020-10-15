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
		/* http://localhost:8080/mvcPage/member/checkId.do?id=kim (get���. ajax����� �����Ǿ� ���޵Ǵ��� �׽�Ʈ) */
		
		/*
		 * ���� ó��
		 */
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		//DAOȣ��
		MemberDAO dao = MemberDAO.getInstance();
		//���̵� �ߺ� üũ
		MemberVO member = dao.checkMember(id);
		
		/*
		 * Jackson ���̺귯�� �̿��Ͽ� JSON ���ڿ� �����ϱ�
		 */
		//HashMap ��ü �����Ͽ� key-value ������ �� ����
		Map<String, String> mapAjax = new HashMap<String, String>();
		if(member == null) {	//���̵� ���ߺ�
			mapAjax.put("result", "idNotFound");
		}else {					//���̵� �ߺ�
			mapAjax.put("result", "idDuplicated");
		}
		
		/*
		 *	JSON �������� ��ȯ�ϱ⸦ ���ϴ� ���ڿ��� HashMap�� key-value ������ ���� ��
		 *	ObjectMapper�� writeValueAsString �޼��忡 Map��ü�� �����Ͽ� '�Ϲ� ���ڿ� ������' -> 'JSON������ ���ڿ� ������'�� ��ȯ �� ��ȯ 
		 */
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP ��� ��ȯ
		return "/WEB-INF/views/common/ajax_singleView.jsp"; //"_singleView.jsp"Ű����� ������ layout.jsp���� include���� �ʰ� �ܵ�ȣ��(������ ������ ������ ��Ģ)
	}

}
