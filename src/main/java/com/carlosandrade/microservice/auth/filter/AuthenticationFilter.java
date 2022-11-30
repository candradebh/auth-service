package com.carlosandrade.microservice.auth.filter;

import com.carlosandrade.microservice.auth.dto.LoginRequestModel;
import com.carlosandrade.microservice.auth.model.UserEntity;
import com.carlosandrade.microservice.auth.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Environment environment;
    private UserService userService;

    public AuthenticationFilter(Environment environment, UserService userService, AuthenticationManager authenticationManager) {
        this.environment = environment;
        this.userService = userService;
        super.setAuthenticationManager(authenticationManager);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        try {
            LoginRequestModel credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));

        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {

        String username = ((User) auth.getPrincipal()).getUsername();

        //se quiser pegar mais dados
        userService.loadUserByUsername(username);

        UserEntity userEntity = userService.getUserDetailsByEmail(username);

        Date dateExpiration = new Date((System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))));

        String token = Jwts.builder().setSubject(userEntity.getId().toString()).setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();

        response.addHeader("token", token);
        response.addHeader("userId", userEntity.getId().toString());
    }

}
