package com.ssamz.web.controller.user;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("로그인 처리");
        
        // 1. 사용자 입력 정보 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 2. DB 연동 처리
        UserVO vo = new UserVO();
        vo.setId(id);
        UserDAO dao = new UserDAO();
        UserVO user = dao.getUser(vo);

        // 3. 화면 이동
        HttpSession session = request.getSession();
        if(user!=null&&user.getPassword().equals(password)){
            // 상태 정보를 세션에 저장한다.
            session.setAttribute("user", user);
            // 글 목록 화면으로 이동한다.
            return "/getBoardList.do";
        }
        return "/loginView.do";
    }
}
