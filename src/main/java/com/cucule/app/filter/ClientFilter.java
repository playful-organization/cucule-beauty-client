package com.cucule.app.filter;

import com.cucule.common.logging.CuculeLogger;

import javax.servlet.*;
import java.io.IOException;

public class ClientFilter implements Filter {
    // protected String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //  encoding = StandardCharsets.UTF_8.name();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //        httpRequest.setCharacterEncoding(encoding);
        CuculeLogger.info(request.getRemoteHost());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
