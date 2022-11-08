package com.example.efolder.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.efolder.exceptions.JwtExpireException;
import com.example.efolder.exceptions.JwtValidationException;
import com.example.efolder.model.Role;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.JwtTokenRequest;
import com.example.efolder.model.dto.respones.JwtTokenInfoResponse;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.efolder.security.SecurityConfig.JWT_SECRET_KEY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {
    private final UserService userService;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream()
                                .map(Role::getRoleName)
                                .collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens =  new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch(Exception exception){
                setResponseHeaders(response, exception);
            }
        } else{
            throw new RuntimeException("Refresh token in missing");
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("auth/verify")
    public JwtTokenInfoResponse isJWTValid(@Valid @NonNull @RequestBody JwtTokenRequest token){

        //String username = "";
        try{
            DecodedJWT decodedJWT =  JWT.require(Algorithm.HMAC256(JWT_SECRET_KEY.getBytes())).build().verify(token.getToken());
        }catch(TokenExpiredException exception){
            throw new JwtExpireException();
        } catch(Exception exception){
            throw new JwtValidationException();
        }
        return new JwtTokenInfoResponse(HttpStatus.OK.value(), "Valid JWT");
    }

    public static void setResponseHeaders(HttpServletResponse response, Exception exception) throws IOException {
        response.setHeader("Error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error =  new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
