package com.spring.krynytsky._1security.config;

import com.spring.krynytsky._1security.models.User;
import com.spring.krynytsky._1security.services.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AllRequestFilter extends GenericFilterBean {
    private User user;

    public AllRequestFilter(UserService userService) {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader != null){
            authorizationHeader.replace("Bearer ", "");
        }
//todo!!!
        filterChain.doFilter(request,response);
    }
}
