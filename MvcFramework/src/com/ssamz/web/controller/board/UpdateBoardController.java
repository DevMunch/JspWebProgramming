package com.ssamz.web.controller.board;

import com.ssamz.biz.board.BoardDAO;
import com.ssamz.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBoardController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("글 수정 처리");

        // 1. 사용자 입력 정보 추출
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
        return "getBoardList.do";
    }
}
