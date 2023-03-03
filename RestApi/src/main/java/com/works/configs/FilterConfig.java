package com.works.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class FilterConfig implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String roles = auth.getAuthorities().toString();
        String name = auth.getName();
        String url = req.getRequestURI();
        String sessionId = req.getSession().getId();
        String userAgent = req.getHeader("user-agent");
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // req.getSession().invalidate();

        System.out.println( name + " " + roles + " " + url + " " + sessionId + " " + userAgent + " " + ipAddress );

        chain.doFilter(request, response);
    }
}
