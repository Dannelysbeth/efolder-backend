package com.example.efolder.model.dto.respones;

import lombok.Data;

@Data
public class JwtTokenInfoResponse {
    private final int status;
    private final String message;
}
