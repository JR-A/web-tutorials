<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date time = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		//HH:24시표기법
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보</title>
</head>
<body>
세션 ID : <%= session.getId() %><br><br>

세션 생성 시간(long타입) : <%= session.getCreationTime() %><br>
<%
	time.setTime(session.getCreationTime());
%>
세션 생선 시간(포맷변환) : <%= sf.format(time) %><br><br>

최근 접속 시간(long) : <%= session.getLastAccessedTime() %><br>
<%
	time.setTime(session.getLastAccessedTime());
%>
최근 접속 시간(포맷변환) : <%= sf.format(time) %><br><br>

세션 유지 시간 변경하기 (기본 세션 유지 시간은 30분)<br>
<%-- 
	방법 1 ) JSP 내에서 변경 (초단위)
		session.setMaxInactiveInterval(60*20); 
	방법 2 ) web.xml에서 변경 (분단위) <-- 권장
		<session-config> 
	  		<session-timeout>50</session-timeout>
	  	</session-config>
--%>
<%
	session.setMaxInactiveInterval(60*20);
	// web.xml에 50분으로 지정된 상태이지만, JSP에서 유지 시간을 20분으로 변경하면 JSP에서 지정한 시간이 우선적으로 적용
%>
세션 유지 시간 : <%= session.getMaxInactiveInterval() %>초<br>	<%-- 세션restart, 전체 브라우저 닫은 후 재실행 --%>

</body>
</html>