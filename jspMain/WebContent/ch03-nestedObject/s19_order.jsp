<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   	<%-- s18_orderForm.jsp 에서 실행 --%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Vector" %>   
<%
	request.setCharacterEncoding("utf-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문서 확인</title>
</head>
<body>
<!-- 
	짜장면 4,000원, 짬뽕 5,000원, 볶음밥 6,000원
	출력예시)
	[주문 음식]
	짜장면 2개
	짬뽕 1개
	총 지불금액 : 13,000원
 -->
 
<%--
 
<%
	HashMap<String, Integer> menu = new HashMap<String, Integer>();
	menu.put("food_c0", 4000);
	menu.put("food_c1", 5000);
	menu.put("food_c2", 6000);
	int sum = 0;
	
	Vector<Integer> num = new Vector<Integer>();
	num.add(Integer.parseInt(request.getParameter("food_c0")));
	num.add(Integer.parseInt(request.getParameter("food_c1")));
	num.add(Integer.parseInt(request.getParameter("food_c2")));
	
	for(int i=0; i<menu.size(); i++){
		sum += menu.get("food_c"+i)*num.get(i);
	}
%>
[주문 음식]<br>
<% if(num.get(0) > 0){ %>
	짜장면 <%= num.get(0) %>개<br>
<% } %>
<% if(num.get(1) > 0){ %>
	짬뽕 <%= num.get(1) %>개<br>
<% } %>
<% if(num.get(2) > 0){ %>
	볶음밥 <%= num.get(2) %>개<br>
<% } %>
총 지불금액 : <%= String.format("%,d", sum) %>원

--%>

<%					    //짜장면 짬뽕 볶음밥
	int[] orderArray = {4000, 5000, 6000};
	
	int total = 0;
	String orderName = "";
	
	int food_c0 = Integer.parseInt(request.getParameter("food_c0")); //짜장면
	int food_c1 = Integer.parseInt(request.getParameter("food_c1")); //짬뽕
	int food_c2 = Integer.parseInt(request.getParameter("food_c2")); //볶음밥
	
	if(food_c0 > 0){
		total += orderArray[0] * food_c0;
		orderName += "짜장면" + food_c0 + "개<br>";
	}
	if(food_c1 > 0){
		total += orderArray[1] * food_c1;
		orderName += "짬뽕" + food_c1 + "개<br>";
	}
	if(food_c2 > 0){
		total += orderArray[2] * food_c2;
		orderName += "볶음밥" + food_c2 + "개<br>";
	}
%>
[주문 음식]<br>
<%= orderName %>
총 지불금액 : <%= String.format("%,d원",total) %>
</body>
</html>