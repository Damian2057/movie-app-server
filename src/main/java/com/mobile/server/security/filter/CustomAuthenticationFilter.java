package com.mobile.server.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mobile.server.controller.pojo.UserLoginRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final String tokenKey;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, String tokenKey) {
        this.authenticationManager = authenticationManager;
        this.tokenKey = tokenKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            String requestData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            UserLoginRequestModel credentials = gson.fromJson(requestData, UserLoginRequestModel.class);
            log.info("Username is: {}", credentials.getUsername());
            log.info("Password is: {}", credentials.getPassword());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException {
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(tokenKey.getBytes());
        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600L * 24 * 7 * 1000 * 4))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("expiration_time", String.valueOf(calendar.getTimeInMillis()));
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
