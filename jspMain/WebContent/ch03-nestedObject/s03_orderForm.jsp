<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 구매</title>
</head>
<body>
<!-- 
이름(name), 날짜(order_date), 상품(item, checkbox, 상품명(가방, 신발, 옷, 식사권)) 전송
 -->
 <form action="s04_order.jsp" method="post">
 이름 : <input type="text" name="name"><br>
 날짜 : <input type="date" name="order_date"><br>
 상품 : (30만원 미만 주문시 배송비 5천원 추가)<br>
 	<input type="checkbox" name="item" value="가방">가방(20만원)
 	<input type="checkbox" name="item" value="신발">신발(10만원)
 	<input type="checkbox" name="item" value="옷">옷(5만원)
 	<input type="checkbox" name="item" value="식사권">식사권(15만원)
 <br>
 <input type="submit" value="전송">
 </form>
</body>
</html>