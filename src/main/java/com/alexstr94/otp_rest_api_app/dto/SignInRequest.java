package com.alexstr94.otp_rest_api_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
    // Когда не проходит валидация - возвращает 403, а не ошибку. ИСПРАВИТЬ!
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Size(min = 5, max = 255, message = "Длина пароля должна быть от 5 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
