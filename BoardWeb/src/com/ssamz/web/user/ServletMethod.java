package com.ssamz.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletMethod {
    private static final long serialVersionUID=1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------------------Start Line--------------------");
        // 요청 방식을 리턴한다.
        String method = request.getMethod();
        // 브라우저가 요청한 URL에서 URI만 리턴한다.
        String uri = request.getRequestURI();
        // 요청에 사용한 프로토콜의 이름/버전을 리턴한다.
        String protocol = request.getProtocol();
        System.out.println(method + " " + uri + " " + protocol);

        System.out.println("------------------Message Header--------------------");

        // String getHeader(String headerName) : headerName에 해당하는 헤더 값을 리턴한다.
        System.out.println("Host : " + request.getHeader("host"));
        System.out.println("Connection : " + request.getHeader("connection"));
        System.out.println("User-Agent : " + request.getHeader("user-agent"));
        System.out.println("Accept : " + request.getHeader("accept"));
        System.out.println("Accept-Encoding : " + request.getHeader("accept-encoding"));
        System.out.println("Accept-Language : " + request.getHeader("accept-language"));
    }
}
