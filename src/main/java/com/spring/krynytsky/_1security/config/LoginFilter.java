package com.spring.krynytsky._1security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.krynytsky._1security.models.User;
import com.spring.krynytsky._1security.services.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private User user;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public LoginFilter(String defaultFilterProcessesUrl,
                       AuthenticationManager authenticationManager,
                       UserService userService,
                       PasswordEncoder passwordEncoder) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        System.out.println(httpServletRequest.getInputStream());
        User user = new ObjectMapper().readValue( httpServletRequest.getInputStream(), User.class);



        if(user != null) {
            System.out.println(user);
            User userDetails = (User)userService.loadUserByUsername(user.getUsername());
            this.user = userDetails;
            if ( userDetails != null){
//                boolean equals = userDetails.getPassword().equals(user.getPassword());
                boolean matches = passwordEncoder
                        .matches(user.getPassword(), userDetails.getPassword());

                System.out.println(matches);
                System.out.println("DONE");

                if (matches) {
                    return getAuthenticationManager()
                            .authenticate(new UsernamePasswordAuthenticationToken
                                    (user.getUsername(), user.getPassword(), userDetails.getAuthorities()));
                }

            }

        }
        return null;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println(this.user + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String token = Jwts.builder()
                .setSubject("qqq")
                .signWith(SignatureAlgorithm.HS512, "key".getBytes())
                .compact();

        this.user.setToken(token);
        userService.saveUser(this.user);
        response.addHeader("Authorization","Bearer " + token );
    }
}
