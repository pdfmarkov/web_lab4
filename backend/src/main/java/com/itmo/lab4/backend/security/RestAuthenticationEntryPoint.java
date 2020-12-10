package com.itmo.lab4.backend.security;

import com.itmo.lab4.backend.exceptions.UserAlreadyExistException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        if(authException instanceof UserAlreadyExistException){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


        PrintWriter writer = response.getWriter();
        writer.println(authException.getMessage());
    }
}
