package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class VerifyDto {
    private String username;
    private String verificationCode;
}
