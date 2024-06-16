package com.isa.PrivateClinicContracts.security.authentication;

import com.isa.PrivateClinicContracts.security.token.JwtTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (shouldBypassJwtValidation(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = getJwtFromRequest(request);
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            processAuthentication(request, jwt, userEmail);
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldBypassJwtValidation(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();
        return path.contains("/api/auth/login");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    private void processAuthentication(HttpServletRequest request, String jwt, String userEmail) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        if (isTokenValid(jwt, userDetails)) {
            setAuthentication(request, userDetails);
        }
    }

    private boolean isTokenValid(String jwt, UserDetails userDetails) {
        boolean isTokenInRepoValid = jwtTokenRepository.findByToken(jwt)
                .map(token -> !token.isExpired() && !token.isRevoked())
                .orElse(false);
        return jwtService.isTokenValid(jwt, userDetails) && isTokenInRepoValid;
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
