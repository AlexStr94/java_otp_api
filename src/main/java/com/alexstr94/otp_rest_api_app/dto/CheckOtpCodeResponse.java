package com.alexstr94.otp_rest_api_app.dto;

import com.alexstr94.otp_rest_api_app.entity.OtpCodeEntity.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckOtpCodeResponse {
    public Status status;
}
