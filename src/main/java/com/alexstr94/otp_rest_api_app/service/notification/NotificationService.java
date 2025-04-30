package com.alexstr94.otp_rest_api_app.service.notification;

import com.alexstr94.otp_rest_api_app.entity.UserEntity;

public interface NotificationService {

    /**
     * Отправка OTP кода
     *
     * @param user пользователь
     * @param otpCode OTP код
     */
    boolean sendOtpCode(UserEntity user, String otpCode);
}
