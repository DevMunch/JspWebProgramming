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

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 0. 상태 정보 체크
        Cookie[] cookieList = request.getCookies();
        if(cookieList==null){ // 쿠키 목록이 없다면 login.html로 이동
            response.sendRedirect("/login.html");
        } else{
            String userId = null;

            for(Cookie cookie : cookieList){
                // userId라는 쿠기 이름이 있는지 찾는다.
                if(cookie.getName().equals("userId")){
                    userId = cookie.getValue(); // userId의 쿠키값을 저장한다.
                }
            }
            if(userId == null){
                response.sendRedirect("/login.html");
            }
        }

        // 1. DB 연동 처리
        BoardVO vo = new BoardVO();

        BoardDAO boardDAO = new BoardDAO();
        List<BoardVO> boardList = boardDAO.getBoardList(vo);

        // 2. 응답 화면 구성
        response.setContentType("text/html; charset=EUC-KR");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>글 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>게시글 목록</h1>");
        out.println("<h3>테스터님 로그인 환영합니다......");
        out.println("<a href='logout.do'>Log-out</a></h3>");

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
