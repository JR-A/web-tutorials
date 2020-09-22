<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 디렉티브</title>
</head>
<body>
이미지 경로 : <%= img_path %><br>
파일 경로 : <%= file_path %><br>
서브 페이지에서 이미지 경로, 파일 경로를 사용할 수 있습니다.
</body>
</html>