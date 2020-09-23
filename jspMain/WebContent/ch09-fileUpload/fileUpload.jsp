<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>	<%-- cos.jar파일의 클래스 --%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %> <%-- cos.jar파일의 클래스 --%>
<%@ page import="java.io.File" %>	<%-- 파일 용량체크 위함 --%>

    <%-- fileForm.jsp 에서 실행 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 처리</title>
</head>
<body>
<%
	String realFolder = "";	//파일 업로드 경로의 절대경로
	
	//파일 업로드 경로
	String saveFolder = "/upload"; //상대경로
	String encType = "utf-8";	//인코딩 타입
	int maxSize = 5*1024*1024;	//파일의 최대 업로드 사이즈(5MB) 1KB=1024byte
	
	//파일 업로드 절대 경로 구하기
	realFolder = application.getRealPath(saveFolder);
	
	out.println("파일 업로드 절대 경로 : " + realFolder + "<br>");	//워크스페이스의 폴더가 아닌, .metadata 내의 경로임
	out.println("---------------------------------<br>");

 //MultipartRequest(HttpServletRequest arg0, String arg1, int arg2, String arg3, FileRenamePolicy arg4)
	MultipartRequest multi = new MultipartRequest(request, 
												  realFolder, //upload 절대 경로 
												  maxSize, //파일 최대 업로드 사이즈
												  encType, //인코딩 타입
							new DefaultFileRenamePolicy() //이미 업로드된 파일과 동일한 파일명일 경우 파일명 교체
							);
	out.println("작성자 : " + multi.getParameter("user") + "<br>");
	out.println("제목 : " + multi.getParameter("title") + "<br>");
	out.println("---------------------------<br>");
	
	//파일 전송 처리
	//전송 전 원래의 파일 이름 반환
	String original = multi.getOriginalFileName("uploadFile");
	//서버에 저장된 파일 이름 반환
	String filename = multi.getFilesystemName("uploadFile");
	//전송된 파일의 컨텐트 타입(문서 타입)
	String type = multi.getContentType("uploadFile");
	
	out.println("전송전 원래의 파일 이름 : " + original + "<br>");
	out.println("서버에 저장된 파일 이름 : " + filename + "<br>");
	out.println("컨텐트 타입 : " + type + "<br>");
	
	//파일의 용량 구하기
	File file = multi.getFile("uploadFile");
	if(file != null){
		out.println("파일 용량 : " + file.length() + "bytes");
	}
%>
</body>
</html>