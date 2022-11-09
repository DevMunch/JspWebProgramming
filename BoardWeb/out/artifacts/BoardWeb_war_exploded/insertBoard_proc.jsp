<%@ page import="com.ssamz.biz.board.BoardVO" %>
<%@ page import="com.ssamz.biz.board.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 1. 사용자 입력 정보 추출
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    // 2. DB 연동 처리
    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setWriter(writer);
    vo.setContent(content);

    BoardDAO boardDAO = new BoardDAO();
    boardDAO.insertBoard(vo);

    // 3. 화면 이동
    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    dispatcher.forward(request, response);
%>