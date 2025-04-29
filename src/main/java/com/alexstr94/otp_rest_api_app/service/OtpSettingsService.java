package com.alexstr94.otp_rest_api_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alexstr94.otp_rest_api_app.entity.OtpSettingsEntity;
import com.alexstr94.otp_rest_api_app.repository.OtpSettingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpSettingsService {
    private final OtpSettingsRepository repository;


    /**
     * Создание или обновление настроек otp кодов.
     * Не дает создать больше одних настроек в БД.
     * 
     * @param settings
     * @return настройки otp кодов.
     */
    public OtpSettingsEntity create(OtpSettingsEntity settings){
        Optional<OtpSettingsEntity> existingSettings = repository.findFirstByOrderByIdAsc();
        if (existingSettings.isPresent()) {
            settings.setId(existingSettings.get().getId());
        }
        return repository.save(settings);
    }

    public Optional<OtpSettingsEntity> getSettings () {
        return repository.findFirstByOrderByIdAsc();
    }
}
