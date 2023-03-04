package com.works.configs;


import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository infoRepository;

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
        Info i = new Info();
        i.setName(name);
        i.setUrl(url);
        i.setRoles(roles);
        i.setUserAgent(userAgent);
        i.setDate(new Date().toString());
        i.setIpAddress(ipAddress);
        i.setSessionId(sessionId);
        infoRepository.save(i);

        chain.doFilter(request, response);
    }
}
