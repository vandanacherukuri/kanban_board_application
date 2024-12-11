package com.pm_app.backend.configs;

import com.pm_app.backend.models.User;
import io.jsonwebtoken.JwtException;
import com.pm_app.backend.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    private final SecurityService securityService;
    private final TokenUtil jwtTokenUtil;

    @Autowired
    public RequestFilter(SecurityService securityService, TokenUtil jwtTokenUtil) {
        this.securityService = securityService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String email = null;
        String token = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);

            try {
                email = jwtTokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Brak możliwości uzyskania tokena");
            } catch (JwtException e) {
                System.out.println(e.getMessage());
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = securityService.loadUserByUsername(email);

            if (jwtTokenUtil.validateToken(token, user)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
