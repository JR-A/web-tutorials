<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- ====================view page 설정==================== --%>
<c:set var="viewPage">
	<jsp:include page="${viewURI}"></jsp:include>
</c:set>
<%-- ====================view page 설정==================== --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mvcPage</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div id="main">
		<div id="main_header">
			<jsp:include page="header.jsp"/>
		</div>
		<div id="main_menu">
			<jsp:include page="left.jsp"/>
		</div>
		<div id="main_body">${viewPage}</div>
		<div id="main_footer">
		    <jsp:include page="footer.jsp"/>
		</div>
	</div>
</body>
</html>
