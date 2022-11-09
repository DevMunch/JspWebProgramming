<%@ page import="com.ssamz.biz.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
    // 1. 컨트롤러(Servlet)가 모델(DAO)을 이용하여 request에 등록한 글 목록을 꺼낸다.
    List<BoardVO> boardVOList = (List) request.getAttribute("boardList");

    //
%>
