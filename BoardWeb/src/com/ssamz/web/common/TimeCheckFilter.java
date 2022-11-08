package com.ssamz.web.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns="*.do") // 어노테이션으로 설정해도 XML 설정과 동일하다.
public class TimeCheckFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    public TimeCheckFilter(){
        System.out.println("===> TimeCheckFilter 생성");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));
        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        System.out.println(path + "요청 처리에 소요된 시간 : " + (endTime - startTime) + "(ms)초");
    }
}
