package com.alexstr94.otp_rest_api_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexstr94.otp_rest_api_app.dto.CheckOtpCodeRequest;
import com.alexstr94.otp_rest_api_app.dto.CheckOtpCodeResponse;
import com.alexstr94.otp_rest_api_app.dto.CreateOtpCodeRequest;
import com.alexstr94.otp_rest_api_app.entity.UserEntity;
import com.alexstr94.otp_rest_api_app.service.OtpService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService service;

    @PostMapping("/create")
    public ResponseEntity<String> createOtp(@RequestBody @Valid CreateOtpCodeRequest request, @AuthenticationPrincipal UserEntity currentUser) {
        service.createOtpCode(request.getOperationUUID(), currentUser);
        
        return ResponseEntity.ok("OTP-код создан.");
    }

    @PostMapping("/check")
    public CheckOtpCodeResponse checkOtpCodeStatus(@RequestBody @Valid CheckOtpCodeRequest request) {
        CheckOtpCodeResponse response = service.checkOtpCode(request.getCode(), request.getOperationUUID());
        return response;
    }
    
    
}
