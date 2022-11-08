package com.ssamz.web.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

// 필터를 Web.xml에 등록하지 않았으므로 어노테이션 방식으로 초기화 파라미터를 지정한다.
@WebFilter(urlPatterns={"/insertBoard.do","/insertUser.do","/updateBoard.do","/getBoardList.do"},
        initParams = @WebInitParam(name="boardEncoding", value="UTF-8")) // 어노테이션으로 설정해도 XML 설정과 동일하다.
public class CharacterEncodingFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    private String encoding;

    // 필터의 init 메서드와 서블릿의 init 메서드는 동일한 기능을 제공하며, 호출되는 시점도 객체 생성 직후로 같다.
    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter("boardEncoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        // 서블릿이 수행되기 전에 인코딩을 처리한다.
//        ServletContext context =request.getServletContext();
//        String encoding = context.getInitParameter("boardEncoding");
        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }
}
