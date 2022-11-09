<%@ page import="com.ssamz.web.user.UserVO" %>
<%@ page import="com.ssamz.web.user.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  // 1. 사용자 입력 정보 추출
  String id = request.getParameter("id");
  String password = request.getParameter("password");

  // 2. DB 연동 처리
  UserVO vo = new UserVO();
  vo.setId(id);

  UserDAO dao = new UserDAO();
  UserVO user = dao.getUser(vo);

  // 3. 화면 이동
  if(user != null){
    // 로그인 성공한 경우
    if(user.getPassword().equals(password)){
      // 상태 정보를 세션에 저장한다.
      session.setAttribute("user",user);

      // 글 목록 화면으로 이동한다.
      response.sendRedirect("/index.jsp");
    }else{
      // 비밀번호가 틀린 경우
      out.println("비밀번호 오류입니다.<br>");
      out.println("<a href='/'>다시 로그인</a>");
    }
  }else{
    // 아이디가 틀린 경우
    out.println("아이디 오류입니다.<br>");
    out.println("<a href='/'>다시 로그인</a>");
  }
%>