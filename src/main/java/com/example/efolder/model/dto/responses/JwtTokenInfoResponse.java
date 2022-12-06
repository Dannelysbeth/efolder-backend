package com.example.efolder.model.dto.responses;

import lombok.Data;

@Data
public class JwtTokenInfoResponse {
    private final int status;
    private final String message;
}
