package com.loginregisration.loginregistration.Security.AuthFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.loginregisration.loginregistration.Tokens.JWT.JwtUtils;
import com.loginregisration.loginregistration.User.UserEntity.UserEntity;
import com.loginregisration.loginregistration.User.UserService.UserService;

import org.springframework.http.HttpHeaders;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String UserEmail;
        final String jwtToken;
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        UserEmail = jwtUtils.extractEmail(jwtToken); 
        if(UserEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserEntity user = userService.loadUserByUsername(UserEmail);
            if (jwtUtils.isTokenValid(jwtToken, user)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                                    user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
