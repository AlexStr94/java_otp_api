package com.alexstr94.otp_rest_api_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpSettingsRequest {
    @NotNull(message = "Необходимо указать время жизни otp кода в секундах.")
    private Integer liveTime;

    @NotNull(message = "Необходимо указать длину otp кода.")
    private Integer symbolsNum;
}
