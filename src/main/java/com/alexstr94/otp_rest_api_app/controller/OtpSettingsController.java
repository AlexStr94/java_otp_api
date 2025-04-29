package com.alexstr94.otp_rest_api_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexstr94.otp_rest_api_app.service.OtpSettingsService;
import com.alexstr94.otp_rest_api_app.dto.OtpSettingsRequest;
import com.alexstr94.otp_rest_api_app.entity.OtpSettingsEntity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpSettingsController {
    private final OtpSettingsService service;

    @PostMapping("/settings")
    @PreAuthorize("hasRole('ADMIN')")
    public OtpSettingsEntity postMethodName(@RequestBody @Valid OtpSettingsRequest settings) {
        return service.create(
                OtpSettingsEntity.builder()
                .liveTime(settings.getLiveTime())
                .symbolsNum(settings.getSymbolsNum())
                .build()
            );
    }

}
