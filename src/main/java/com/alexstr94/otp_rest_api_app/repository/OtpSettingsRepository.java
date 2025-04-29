package com.alexstr94.otp_rest_api_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexstr94.otp_rest_api_app.entity.OtpSettingsEntity;

@Repository
public interface OtpSettingsRepository extends JpaRepository<OtpSettingsEntity, Integer>{
    Optional<OtpSettingsEntity> findFirstByOrderByIdAsc();
}
