package com.alexstr94.otp_rest_api_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexstr94.otp_rest_api_app.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long>{
    
}
