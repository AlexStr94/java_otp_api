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

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadOtpCodes(@AuthenticationPrincipal UserEntity currentUser) throws IOException {
        InputStreamResource resource = service.getUserCodes(currentUser);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=otp_codes.txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }
   
}
