package kr.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;		//cos.jar������ Ŭ����
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy; //cos.jar������ Ŭ����. �̹� ���ε�� ���ϰ� ������ ���ϸ��� ��� ���ϸ� ��ü

/*
 *	���� ���ε� ����� �����ϱ� ���� cos.jar ������ �ʼ������� �䱸��(cos���̺귯�� ���)  
 */
public class FileUtil {
	//���ε� ���(������)
	public static final String UPLOAD_PATH = "E:\\ezen\\javaWork\\workspace_jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvcPage\\upload";
	//���ڵ� Ÿ��
	public static final String ENCODING_TYPE = "UTF-8";
	//�ִ� ���ε� ������
	public static final int MAX_SIZE = 10*1024*1024;	//10MB (1KB=1024byte)
	
	//���� ó��
	//������ ���ε� ��ο� ����
	public static MultipartRequest createFile(HttpServletRequest request)throws IOException {
		//�Ű������� ���޹��� request �̿��Ͽ� MulipartRequest ��ü �����Ͽ� ��ȯ��
																				//�̹� ���ε�� ���ϰ� ������ ���ϸ��� ��� ���ϸ� ��ü
		return new MultipartRequest(request, UPLOAD_PATH, MAX_SIZE, ENCODING_TYPE, new DefaultFileRenamePolicy());
	}
	
	//���� ����
	public static void removeFile(String filename) {
		if(filename != null) {
			File file = new File(UPLOAD_PATH, filename);
			if(file.exists()) file.delete();	//���ε��ο� ���� �̸��� �ش��ϴ� ������ �����ϸ� ���� ����
		}
	}
}
