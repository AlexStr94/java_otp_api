package com.alexstr94.otp_rest_api_app.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateOtpCodeRequest {
    private UUID operationUUID;
}
