package com.alexstr94.otp_rest_api_app.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alexstr94.otp_rest_api_app.dto.CheckOtpCodeResponse;
import com.alexstr94.otp_rest_api_app.entity.OtpCodeEntity;
import com.alexstr94.otp_rest_api_app.entity.OtpSettingsEntity;
import com.alexstr94.otp_rest_api_app.entity.UserEntity;
import com.alexstr94.otp_rest_api_app.entity.OtpCodeEntity.Status;
import com.alexstr94.otp_rest_api_app.exception.ApiException;
import com.alexstr94.otp_rest_api_app.repository.OtpRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository repository;
    private final OtpSettingsService otpSettingsService;
    @Value("${otp-settings:default-live-time}")
    private String otpCodeLiveTimeDefault;

    private boolean checkOtpCodeValid(OtpCodeEntity otpCodeEntity) {
        Optional<OtpSettingsEntity> otpSettings = otpSettingsService.getSettings();
        Integer otpCodeLiveTime;
        if (otpSettings.isPresent()){
            otpCodeLiveTime = otpSettings.get().getLiveTime();
        } else {
            otpCodeLiveTime = Integer.parseInt(otpCodeLiveTimeDefault);
        }
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(otpCodeEntity.getCreateDateTime(), now);
        if (duration.getSeconds() < otpCodeLiveTime) {
            return true;
        }
        return false;
    }

    public void createOtpCode(UUID operationUUID, UserEntity user){
        Random random = new Random();
        StringBuilder code = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        var otpEntity = OtpCodeEntity.builder()
            .user(user)
            .operationUuid(operationUUID)
            .code(code.toString())
            .status(Status.ACTIVE)
            .build();
        
        repository.save(otpEntity);
    }

    public CheckOtpCodeResponse checkOtpCode(String code, UUID operationUUID) {
        OtpCodeEntity otpCode = new OtpCodeEntity();
        otpCode.setCode(code);
        otpCode.setOperationUuid(operationUUID);
        Example<OtpCodeEntity> example = Example.of(otpCode);
        Optional<OtpCodeEntity> result = repository.findOne(example);
        if (result.isPresent()) {
            otpCode = result.get();
            Status status = otpCode.getStatus();
            if (!checkOtpCodeValid(otpCode)) {
                otpCode.setStatus(Status.EXPIRED);
            } else {
                otpCode.setStatus(Status.USED);
            }
            repository.save(otpCode);
            return CheckOtpCodeResponse.builder().status(status).build();
        }
        throw new ApiException(Collections.singletonMap("code", "Код для данной операции не найден."));
    }

}
