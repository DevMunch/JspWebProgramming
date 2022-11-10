package com.ssamz.web.controller.board;

import com.ssamz.biz.board.BoardDAO;
import com.ssamz.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBoardController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("글 상세 조회 처리");
        
        // 1. 사용자 입력 정보 추출
        String seq = request.getParameter("seq");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        BoardVO board = boardDAO.getBoard(vo);

        // 3. 화면 이동
        request.setAttribute("board", board);
        return "getBoard";
    }
}
