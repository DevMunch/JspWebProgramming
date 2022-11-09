package com.ssamz.web.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/getBoardList.do","/getBoard.do","/deleteBoard.do"})
public class AuthenticationFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 세션에 userId 정보가 등록되어 있는지 확인한다.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if(session.getAttribute("user")==null){
            res.sendRedirect("/");
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
