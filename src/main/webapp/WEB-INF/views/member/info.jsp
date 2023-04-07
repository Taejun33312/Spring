<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>info.jsp</h1>
	
	아이디 : ${memberVO.userid } <br>
	이름 : ${memberVO.username } <br>
	이메일 : ${memberVO.useremail } <br>
	가입일 : ${memberVO.regdate } <br>
</body>
</html>