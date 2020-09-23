<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다중 업로드 예제</title>
</head>
<body>
<form action="multiFileUpload.jsp" method="post" enctype="multipart/form-data">
	제목 <input type="text" name="subject"><br>
	이미지 1 <input type="file" name="uploadFile1" accept="image/*"><br>
	이미지 2 <input type="file" name="uploadFile2" accept="image/*"><br>
	<input type="submit" value="파일 올리기">
</form>
</body>
</html>