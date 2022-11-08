package com.example.efolder.model.dto.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtTokenRequest {
    @NotBlank
    String token;
}
