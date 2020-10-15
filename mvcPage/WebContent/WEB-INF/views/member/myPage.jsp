<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-main-style">
	<h2>My Info</h2>
	<ul>	<%-- .key로 value접근 --%>
		<li>이름 : ${member.name}</li>
		<li>전화번호 : ${member.phone}</li>
		<li>이메일 : ${member.email}</li>
		<li>우편번호 : ${member.zipcode}</li>
		<li>주소 : ${member.address1} ${member.address2}</li>
		<li>가입일 : ${member.reg_date}</li>
		<li>정보 수정일 : ${member.modify_date}</li>
	</ul>
	<hr size="1" width="100%" noshade="noshade"> <%--구분선. noshade입체감 없도록 --%>
	<div class="align-right">
		<input type="button" value="비밀번호 수정" onclick="location.href='modifyPasswordForm.do'">
		<input type="button" value="회원정보 수정" onclick="location.href='modifyUserForm.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'">
	</div>
</div>