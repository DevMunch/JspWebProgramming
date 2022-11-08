package com.ssamz.biz.board;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateBoard.do")
public class UpdateBoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String encoding;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 인코딩 설정
        ServletContext context = getServletContext();
        this.encoding = context.getInitParameter("boardEncoding");

        // 1. 사용자 입력 정보 추출
        request.setCharacterEncoding(encoding);
        String title = request.getParameter("title");
        String seq = request.getParameter("seq");
        String content = request.getParameter("content");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setSeq(Integer.parseInt(seq));
        vo.setContent(content);

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.updateBoard(vo);

        // 3. 화면 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
        dispatcher.forward(request, response);
    }
}
