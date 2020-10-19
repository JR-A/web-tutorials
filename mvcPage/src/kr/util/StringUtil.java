package kr.util;

public class StringUtil {
	/*
  	 * html������ �ٹٲ��� <br>
  	 * java������ �ٹٲ��� \n �̹Ƿ� html�� ǥ���ϸ� �ٹٲ��� ���õ� (��쿡 ���� \r\n, \r�� ����)
  	 */
	
	/*
	 * HTML �±� ����ϸ鼭 �ٹٲ�
	 */
	public static String useBrHtml(String str) {
		if(str == null) return null;
		
		//chain�ɱ� (replaceAll�޼��� ȣ�� �� ��ȯ�Ǵ� �͵� ���� ���ڿ��̹Ƿ� replaceAll�޼��� �����Ͽ� ȣ�� ����)
		return str.replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	/*
	 * HTML �±� ������� �����鼭 �ٹٲ� 
	 */
	public static String useBrNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")		//little
				  .replaceAll(">", "&gt;")		//greater
				  .replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	/*
	 * HTML �±� ������� �����鼭 �ٹٲ޵� ������� ���� 
	 */
	public static String useNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")		//little
				  .replaceAll(">", "&gt");		//greater
	}
}
