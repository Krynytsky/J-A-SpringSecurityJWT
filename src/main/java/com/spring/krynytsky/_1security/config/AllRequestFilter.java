package com.spring.krynytsky._1security.config;

import com.spring.krynytsky._1security.models.User;
import com.spring.krynytsky._1security.services.user.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AllRequestFilter extends GenericFilterBean {
    private User user;
    private UserService userService;

    public AllRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader != null){
            String token = authorizationHeader.replace("Bearer ", "");
//            Jwts.parser()
//                    .setSigningKey("key".getBytes())
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
            User userByToken = this.userService.findByToken(token);
            authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
                    user.getAuthorities());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }
}
