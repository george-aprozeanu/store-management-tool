package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

//@Component
public class JwtTokenFilter implements jakarta.servlet.Filter {

    private final JwtService jwt;
    private final Base64.Decoder decoder = Base64.getDecoder();

    JwtTokenFilter(JwtService jwt) {
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            var header = httpServletRequest.getHeader("Authorization");
            System.out.println("filter jwt " + header);
            if (header != null) {
                var lcHeader = header.toLowerCase();
                if (lcHeader.startsWith("bearer")) {
                    var bearer = header.substring("bearer ".length()).trim();
                    System.out.println("bearer!!!! " + bearer);
                    var result = jwt.decrypt(bearer);
                    if (result instanceof JwtService.Result.Success success) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        System.out.println("Somebody stop it!");
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
