<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
										<!-- 폼이 제출될 때의 형식 명시. 파일이나 이미지를 서버로 전송시 -->
<form action="fileUpload.jsp" method="post" enctype="multipart/form-data">
	작성자 <input type="text" name="user"><br>
	제목 <input type="text" name="title"><br>
	파일명 <input type="file" name="uploadFile"><br>
	<input type="submit" value="파일 올리기">
</form>
</body>
</html>