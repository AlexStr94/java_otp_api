package com.alexstr94.otp_rest_api_app.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CheckOtpCodeRequest {
    public String code;
    public UUID operationUUID;
}
