package com.alexstr94.otp_rest_api_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexstr94.otp_rest_api_app.dto.UserDto;
import com.alexstr94.otp_rest_api_app.entity.UserEntity;
import com.alexstr94.otp_rest_api_app.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserListController {
    private final UserService userService;

    /**
     * Возвращает список всех пользователей
     * 
     * @return List<UserDto>
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> postMethodName() {
        List<UserEntity> userEntities = userService.getUsers();
        return userEntities.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build()).collect(Collectors.toList());
    }
}
    

