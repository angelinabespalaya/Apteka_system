package com.angelina.bespalaya_apteka.controller;

import com.angelina.bespalaya_apteka.dto.LoginRequest;
import com.angelina.bespalaya_apteka.dto.LoginResponse;
import com.angelina.bespalaya_apteka.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API контроллер для управления аутентификацией пользователей.
 * <p>
 * Этот класс предоставляет эндпоинт для входа в систему.
 * </p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Сервис для выполнения операций аутентификации.
     */
    private final AuthService authService;

    /**
     * Конструктор для создания экземпляра AuthController.
     *
     * @param authService Сервис, используемый для аутентификации пользователей.
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Эндпоинт для аутентификации пользователя.
     *
     * @param loginRequest Объект, содержащий данные для входа (логин и пароль).
     * @return Ответ с сообщением об успешной аутентификации или ошибке.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok(new LoginResponse("successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid"));
        }
    }
}

