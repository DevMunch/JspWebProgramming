package com.ssamz.web.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 로그인 인증처리
        // 1. 사용자 입력 정보 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 2. DB 연동 처리
        UserVO vo = new UserVO();
        vo.setId(id);

        UserDAO dao = new UserDAO();
        UserVO user = dao.getUser(vo);

        // 3. 응답 화면 구성
        // 응답 메시지에 대한 인코딩 설정
        response.setContentType("text/html;charset=UTF-8");
        // HTTP 응답 프로토콜 message-body와 연결된 출력 스트림 획득
        PrintWriter out = response.getWriter();

        if(user != null){
            // 로그인 성공한 경우
            if(user.getPassword().equals(password)){
                // 상태 정보를 세션에 저장한다.
                HttpSession session = request.getSession();
                //session.setMaxInactiveInterval(10); 세션의 유효시간 10초
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());
                session.setAttribute("userRole", user.getRole());

                // 글 목록 화면으로 포워딩한다.
                // 글 목록 화면에서 사용할 데이터를 ServletContext에 등록한다.
                ServletContext context = getServletContext();
                context.setAttribute("welcomeMessage", "님 환영합니다.");
                response.sendRedirect("getBoardList.do");
            }else{
                // 비밀번호가 틀린 경우
                out.println("비밀번호 오류입니다.<br>");
                out.println("<a href='/'>다시 로그인</a>");
            }
        }else{
            // 아이디가 틀린 경우
            out.println("아이디 오류입니다.<br>");
            out.println("<a href='/'>다시 로그인</a>");
        }
    }
}
