<%@ page import="com.ssamz.web.user.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 프로그램</title>
</head>
<body>
<hr>
<a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;
<%
    UserVO user = (UserVO) session.getAttribute("user");
%>
<%  if(user==null){ %>
<a href="insertUser.jsp">회원가입</a>&nbsp;&nbsp;&nbsp;
<a href="login.jsp">로그인</a>&nbsp;&nbsp;&nbsp;
<%}else{%>
<a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;&nbsp;
<a href="logout.jsp">로그아웃</a>&nbsp;&nbsp;&nbsp;
<%}%>

<hr>
<br>
