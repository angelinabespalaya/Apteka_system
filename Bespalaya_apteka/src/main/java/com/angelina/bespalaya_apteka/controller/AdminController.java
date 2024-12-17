package com.angelina.bespalaya_apteka.controller;

import com.angelina.bespalaya_apteka.entity.User;
import com.angelina.bespalaya_apteka.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API контроллер для управления пользователями в админской панели.
 * <p>
 * Этот класс предоставляет эндпоинты для выполнения операций с пользователями,
 * доступных только пользователям с ролью "ADMIN".
 * </p>
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /**
     * Сервис для управления пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для создания экземпляра AdminController.
     *
     * @param userService Сервис, используемый для выполнения операций с пользователями.
     */
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Эндпоинт для получения списка всех пользователей.
     * <p>
     * Этот метод доступен только пользователям с ролью "ADMIN".
     * </p>
     *
     * @return ResponseEntity, содержащий список пользователей.
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}

