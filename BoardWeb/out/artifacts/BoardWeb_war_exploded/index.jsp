<%@ page import="com.ssamz.biz.board.BoardVO" %>
<%@ page import="com.ssamz.biz.board.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="errors/boardError.jsp"
         session="false"
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%@ include file="layout/header.jsp"%>

  <%
    // 1. DB 연동 처리
    BoardVO vo = new BoardVO();
    vo.setSearchCondition("TITLE");
    vo.setSearchKeyword("");

    BoardDAO dao = new BoardDAO();
    List<BoardVO> boardList = dao.getBoardList(vo);

    // 2. 화면 이동
  %>
<center>
<!-- 검색 시작 -->
<form action="getBoardList.jsp" method="post">
  <table border="1" cellpadding="0" cellspacing="0" width="700">
    <tr>
      <td align="right">
        <select name="searchCondition">
          <option value="TITLE">제목</option>
          <option value="CONTENT">내용</option>
        </select>
        <input name="searchKeyword" type="text"/>
        <input type="submit" value="검색"/>
      </td>
    </tr>
  </table>
</form>
<!-- 검색 종료 -->
<table border="1" cellspacing="0" cellpadding="0" width="700">
  <tr>
    <th bgcolor="orange" width="100">번호</th>
    <th bgcolor="orange" width="200">제목</th>
    <th bgcolor="orange" width="150">작성자</th>
    <th bgcolor="orange" width="150">등록일</th>
    <th bgcolor="orange" width="100">조회수</th>
  </tr>
  <% for(BoardVO board : boardList){%>
  <tr>
    <td><%=board.getSeq()%></td>
    <td align="left"><a href="getBoard.jsp"><%=board.getTitle()%></a></td>
    <td><%=board.getWriter()%></td>
    <td><%=board.getRegDate()%></td>
    <td><%=board.getCnt()%></td>
  </tr>
  <%}%>
</table>
</center>
  <%@ include file="layout/footer.jsp"%>
  </body>
</html>
