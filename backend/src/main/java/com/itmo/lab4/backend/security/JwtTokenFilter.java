package com.itmo.lab4.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private RestAuthenticationEntryPoint entryPoint;

    private JwtTokenProvider jwtTokenProvider;
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        Optional<String> token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if(!token.isPresent()) {
            filterChain.doFilter(req, res);
            return;
        }

        if (!jwtTokenProvider.validateToken(token.get())) {
            makeResponse((HttpServletResponse)res, HttpServletResponse.SC_UNAUTHORIZED,
                    "Expired or invalid JWT token");
        } else {
            Authentication auth = jwtTokenProvider.getAuthentication(token.get());
            String[] pathParts = ((HttpServletRequest)req).getServletPath().split("/");
            if(pathParts.length >= 3 && pathParts[1].equals("users") &&
            !pathParts[2].equals(((UserDetails)auth.getDetails()).getUsername())){
                makeResponse((HttpServletResponse)res, HttpServletResponse.SC_FORBIDDEN, "This is not your resource");
            } else {
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(req, res);
            }
        }
    }

    private void makeResponse(HttpServletResponse response, int httpStatus, String message) throws IOException {
        response.setStatus(httpStatus);
        response.getWriter().println(message);
    }
}
