package com.spring.krynytsky._1security.config;

import com.spring.krynytsky._1security.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
//                .antMatchers("/product/products").hasAnyRole("USER","ADMIN")
//                .antMatchers("/product/**").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/product/save").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/saveUser").permitAll()
                .antMatchers(HttpMethod.POST,"/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .and()
                .addFilterBefore(new AllRequestFilter(userService),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new LoginFilter("/login", authenticationManager(), userService, passwordEncoder()), UsernamePasswordAuthenticationFilter.class);

    }

//Authentication via Database
    @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
// End Authentication via Database
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

//Inmemory Authenticacion Method
//   @Override
//       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//           auth.inMemoryAuthentication()
//                   .withUser("admin")
//                   .password("{noop}admin")
//                   .roles("ADMIN");
//           auth.inMemoryAuthentication()
//                   .withUser("user")
//                   .password("{noop}user")
//                   .roles("USER");
//   }
// End Inmemory Authenticacion Method

}

