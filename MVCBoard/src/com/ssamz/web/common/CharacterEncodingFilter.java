package com.ssamz.web.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter(urlPatterns={"*.do"}, initParams = @WebInitParam(name="boardEncoding", value="UTF-8"))
public class CharacterEncodingFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    private String encoding;

    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter("boardEncoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
