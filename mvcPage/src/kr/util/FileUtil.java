package kr.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;		//cos.jar파일의 클래스
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy; //cos.jar파일의 클래스. 이미 업로드된 파일과 동일한 파일명일 경우 파일명 교체

/*
 *	파일 업로드 기능을 수행하기 위해 cos.jar 파일이 필수적으로 요구됨(cos라이브러리 사용)  
 */
public class FileUtil {
	//업로드 경로(절대경로)
	public static final String UPLOAD_PATH = "E:\\ezen\\javaWork\\workspace_jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvcPage\\upload";
	//인코딩 타입
	public static final String ENCODING_TYPE = "UTF-8";
	//최대 업로드 사이즈
	public static final int MAX_SIZE = 10*1024*1024;	//10MB (1KB=1024byte)
	
	//파일 처리
	//파일을 업로드 경로에 생성
	public static MultipartRequest createFile(HttpServletRequest request)throws IOException {
		//매개변수로 전달받은 request 이용하여 MulipartRequest 객체 생성하여 반환ㄴ
																				//이미 업로드된 파일과 동일한 파일명일 경우 파일명 교체
		return new MultipartRequest(request, UPLOAD_PATH, MAX_SIZE, ENCODING_TYPE, new DefaultFileRenamePolicy());
	}
	
	//파일 삭제
	public static void removeFile(String filename) {
		if(filename != null) {
			File file = new File(UPLOAD_PATH, filename);
			if(file.exists()) file.delete();	//업로드경로에 파일 이름에 해당하는 파일이 존재하면 파일 삭제
		}
	}
}
