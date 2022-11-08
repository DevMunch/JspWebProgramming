package com.ssamz.biz.board;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getBoardList.do")
public class GetBoardListServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1. 사용자 입력 정보 추출
        String searchCondition = request.getParameter("searchCondition");
        String searchKeyword = request.getParameter("searchKeyword");

        // Null Check
        if(searchCondition==null) searchCondition = "TITLE";
        if(searchKeyword==null) searchKeyword = "";

        // 세션에 검색 관련 정보를 저장한다.
        HttpSession session = request.getSession();
        session.setAttribute("condition", searchCondition);
        session.setAttribute("keyword", searchKeyword);

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setSearchCondition(searchCondition);
        vo.setSearchKeyword(searchKeyword);

        BoardDAO boardDAO = new BoardDAO();
        List<BoardVO> boardList = boardDAO.getBoardList(vo);

        // 3. 응답 화면 구성
        response.setContentType("text/html; charset=EUC-KR");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>글 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>게시글 목록</h1>");
        String userName = (String) session.getAttribute("userName");
//        String welcomeMessage = (String) context.getAttribute("welcomeMessage");
        out.println("<h3>"+ userName + "님 환영합니다.");
        out.println("<a href='logout.do'>Log-out</a></h3>");

        out.println("<!-- 검색 시작 -->");
        out.println("<form action='getBoardList.do' method='post'>");
        out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
        out.println("<tr>");
        out.println("<td align='right'>");
        out.println("<select name='searchCondition'>");

        String condition = (String)session.getAttribute("condition");
        if(condition.equals("TITLE")){
            out.println("<option value='TITLE' selected>제목");
        }else{
            out.println("<option value='TITLE'>제목");
        }
        if(condition.equals("CONTENT")){
            out.println("<option value='CONTENT' selected>내용");
        }else{
            out.println("<option value='CONTENT'>내용");
        }

        out.println("</select>");
        out.println("<input name='searchKeyword' type='text'" +
                "value='"+session.getAttribute("keyword") + "'/>");
        out.println("<input type='submit' value='검색'/>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("<!-- 검색 종료 -->");

        out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
        out.println("<tr>");
        out.println("<th bgcolor='orange' width='100'>번호</th>");
        out.println("<th bgcolor='orange' width='200'>제목</th>");
        out.println("<th bgcolor='orange' width='150'>작성자</th>");
        out.println("<th bgcolor='orange' width='150'>등록일</th>");
        out.println("<th bgcolor='orange' width='100'>조회수</th>");
        out.println("</tr>");

        for(BoardVO board : boardList){
            out.println("<tr>");
            out.println("<td>" + board.getSeq() + "</td>");
            out.println("<td align='left'><a href='getBoard.do?seq="+board.getSeq() +"'>" + board.getTitle() + "</a></td>");
            out.println("<td>" + board.getWriter() + "</td>");
            out.println("<td>" + board.getRegDate() + "</td>");
            out.println("<td>" + board.getCnt() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br>");
        out.println("<a href='insertBoard.html'>새글 등록</a>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
