package com.alexstr94.otp_rest_api_app.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ApiException extends Error {
    final private Map<String, String> errors;

}

