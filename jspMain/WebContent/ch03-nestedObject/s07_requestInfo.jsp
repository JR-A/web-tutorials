<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클라이언트 및 서버 정보</title>
</head>
<body>
<!-- ip 버전6 -->
<!-- http://192.168.0.53:8080/jspMain ~~ ip주소 변경 -->
클라이언트 IP = <%= request.getRemoteAddr() %><br>  							<!-- 중요 -->
요청 정보의 프로토콜 = <%= request.getProtocol() %><br>
요청 정보의 전송방식 = <%= request.getMethod() %><br> 								<!-- 중요 -->
<!-- 서버 밖에서 서버 내의 자원 호출할 때(서버 외에서 호출할 경우) 절대경로 모두 알아야 -->
요청 URL(Uniform Resource Locator) = <%= request.getRequestURL() %><br> 		<!-- 중요 -->
<!-- 같은 서버의 서버프로그램에서 다른 서버프로그램에 데이터 요청할 경우(같은 서버에서 호출할경우) context root부터 시작 -->
요청 URI(Uniform Resource Identifier) = <%= request.getRequestURI() %><br> 	<!-- 중요 -->
컨텍스트(하나의 프로그램) 경로 = <%= request.getContextPath() %><br> 				<!-- 중요 -->
서버 이름 = <%= request.getServerName() %><br>
서버 포트 = <%= request.getServerPort() %><br>
</body>
</html>