<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 모듈화</title>
<style type="text/css">
	table{
		border-collapse: collapse;	/* 테이블 테두리 1줄로 */
		border: 1px solid #000000;
		width: 600px;
		margin: 0 auto;
	}
	td{
		border: 1px solid #000000;
		text-align: center;
		vertical-align: middle;
		height: 40px;
	}
	td.td-middle{
		text-align: left;
		vertical-align: top;
		height: 200px;
	}
	td.td-width{
		width: 100px;
	}
</style>
</head>
<body>
<table>
	<tr>
		<td colspan="2"> <!-- 셀 2칸을 병합 -->
			<jsp:include page="/ch06-actionTag/module/top.jsp">
				<jsp:param value="스타" name="company"/>
			</jsp:include>
		</td>
	</tr>
	<tr>
		<td class="td-middle td-width">
			<jsp:include page="/ch06-actionTag/module/left.jsp"/>	<!-- 내용 없는 경우 태그 끝에 /추가해 단독태그로 사용가능 -->
		</td>
		<td class="td-middle">
			<!-- 내용 부분 시작 -->
			레이아웃1
			<br><br><br>
			<!-- 내용 부분 끝 -->
		</td>
	</tr>
	<tr>
		<td colspan="2"> <!-- 셀 2칸을 병합 -->
			<jsp:include page="/ch06-actionTag/module/bottom.jsp"/>----------
		</td>
	</tr>
</table>
</body>
</html>