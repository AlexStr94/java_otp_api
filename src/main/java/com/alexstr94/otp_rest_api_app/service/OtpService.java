package com.alexstr94.otp_rest_api_app.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alexstr94.otp_rest_api_app.entity.OtpEntity;
import com.alexstr94.otp_rest_api_app.entity.UserEntity;
import com.alexstr94.otp_rest_api_app.entity.OtpEntity.Status;
import com.alexstr94.otp_rest_api_app.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository repository;

    public void createOtpCode(UUID operationUUID, UserEntity user){
        Random random = new Random();
        StringBuilder code = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        var otpEntity = OtpEntity.builder()
            .user(user)
            .operationUuid(operationUUID)
            .code(code.toString())
            .status(Status.ACTIVE)
            .build();
        
        repository.save(otpEntity);
    }

}
