package com.alexstr94.otp_rest_api_app.service;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexstr94.otp_rest_api_app.entity.UserEntity;
import com.alexstr94.otp_rest_api_app.entity.UserEntity.Role;
import com.alexstr94.otp_rest_api_app.exception.ApiException;
import com.alexstr94.otp_rest_api_app.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public UserEntity create(UserEntity user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new ApiException(Collections.singletonMap("username", "Пользователь с таким именем уже существует"));
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new ApiException(Collections.singletonMap("email", "Пользователь с таким email уже существует"));
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserEntity getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }



    /**
     * Получение пользователя с ролью админ
     * 
     * @return
     */
    public Optional<UserEntity> getAdmin() {
        UserEntity admin = new UserEntity();
        admin.setRole(Role.ROLE_ADMIN);
        Example<UserEntity> example = Example.of(admin);
        return repository.findOne(example);
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}