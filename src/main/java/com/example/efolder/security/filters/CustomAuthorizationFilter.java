package com.example.efolder.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.efolder.api.TokenController;
import com.example.efolder.api.handlers.DTO.ErrorResponse;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.example.efolder.security.SecurityConfig.*;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("api/login") || request.getServletPath().equals("api/token/refresh") || request.getServletPath().startsWith("api/token/documents/")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception exception) {
                    log.error(exception.getMessage());
                    TokenController.setResponseHeaders(response, exception);
                }
//            }
//            if(isAuthorizationHeaderCorrect(authorizationHeader)) {
//                try {
//                    String token = authorizationHeader.substring("Bearer ".length());
//
//                    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JWT_SECRET_KEY.getBytes())).build().verify(token);
//                    String username = decodedJWT.getSubject();
//
//                    Map<String, Claim> JWTClaims = decodedJWT.getClaims();
//                    UsernamePasswordAuthenticationToken authenticationToken;
//                    User user = userService.getUser(username);
//                    //TODO fix this issue
//                    authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    filterChain.doFilter(request, response);
//
//                } catch (TokenExpiredException exception) {
//                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "JWT expired");
//
//                } catch (JWTVerificationException exception) {
//                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "JWT validation error");
//
//                } catch (Exception exception) {
//                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "Authorization denied. Error occured.");
//                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private void addErrorMessageToResponse(HttpServletResponse response, int errorCode, String errorMessage) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(errorCode, errorMessage));
    }

    private boolean isRequestURLAvailableForNotLoggedInUsers(HttpServletRequest request) {
        return request.getServletPath().equals(LOGIN_URL) || request.getServletPath().equals(REFRESH_URL);
    }

    private boolean isAuthorizationHeaderCorrect(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
