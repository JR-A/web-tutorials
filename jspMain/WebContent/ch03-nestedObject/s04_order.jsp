<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  	<%-- s03_orderFormjsp 에서 실행 --%>
<%@ page import="java.util.HashMap" %>  
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
%>
<%--
	구매 금액이 30만원 미만이면 배송비 5천원 추가
	[출력예시]
	이름 : 
	날짜 : 
	구매 상품 :
	배송비 : 
	총구매비용(배송비 포함) :
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 내역</title>
</head>
<body>
<h3>구매 내역</h3>
이름(name) : <%= request.getParameter("name") %><br>
날짜(order_date) : <%= request.getParameter("order_date") %><br>
상품(item) : 
<% 	
	String[] values = request.getParameterValues("item");
	if(values != null){
		int i=0;
		for(i=0; i<values.length-1; i++){
%>
			<%= values[i] %>, 		
<%
		}
%>
		<%= values[i] %>		
<%
	}
%><br>


	<%-- 배열 사용 --%>
	<% 
		String[] category = {"가방", "신발", "옷", "식사권"};
		int[] prices = {200000, 100000, 50000, 150000};
		int sum = 0;
		
		if(values!=null){
			for(int i=0; i<values.length; i++){
				for(int j=0; j<category.length; j++){
					if( values[i].equals(category[j])){
						sum += prices[j];
					}
				}
			}
			if(sum < 300000){	//30만원 미만일시 배송비 5000원 추가
				sum += 5000;
			}
			
		}
	%>
	총 구매 비용(배송비 포함) : <%= sum %>원<br>
	
	<%--  ======================= --%>
	<%-- hashmap 사용 --%>
	<% 
		//상품의 가격 저장
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("가방", 200000);
		map.put("신발", 100000);
		map.put("옷", 50000);
		map.put("식사권", 150000);
		sum = 0;
		
		//기본 배송비
		int deliveryFee = 5000;
		
		if(values != null){
			for(int i=0; i<values.length; i++){
				//구매 상품의 금액을 누적
				sum += map.get(values[i]);
			}
			//배송비
			if(sum < 300000){
				sum += deliveryFee;
			}else{
				deliveryFee = 0;
			}
		}
		
	%>
	배송비 : <%= deliveryFee %><br>
	총 구매 비용(배송비 포함) : <%= sum %>원
	
</body>
</html>