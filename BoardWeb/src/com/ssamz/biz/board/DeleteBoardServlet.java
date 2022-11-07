package com.ssamz.biz.board;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteBoard.do")
public class DeleteBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        // 1. 사용자 입력 정보 추출
        String seq = request.getParameter("seq");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.deleteBoard(vo);

        // 3. 화면 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
        dispatcher.forward(request, response);
    }
}
