package com.angelina.bespalaya_apteka.controller;

import com.angelina.bespalaya_apteka.entity.User;
import com.angelina.bespalaya_apteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST API контроллер для управления ролями пользователей.
 * <p>
 * Этот класс предоставляет эндпоинты для изменения роли пользователей и удаления пользователей,
 * доступные только пользователям с ролью "ADMIN".
 * </p>
 */
@Controller
@RequestMapping("api/admin/users")
public class AdminUserController {

    /**
     * Сервис для управления пользователями.
     */
    @Autowired
    private UserService userService;

    /**
     * Эндпоинт для изменения роли пользователя.
     * <p>
     * Этот метод принимает JSON-объект с ID пользователя и новой ролью.
     * </p>
     *
     * @param userData Map с данными пользователя ("id" и "role").
     * @return ResponseEntity с кодом ответа 200 (OK) или 404 (Not Found).
     */
    @PostMapping("/update")
    public ResponseEntity<Void> updateUserRole(@RequestBody Map<String, Object> userData) {
        Long userId = Long.valueOf(userData.get("id").toString());
        String newRole = userData.get("role").toString();
        User user = userService.findById(userId);
        if (user != null) {
            user.setRole(newRole);
            userService.update(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Эндпоинт для удаления пользователя.
     * <p>
     * Этот метод принимает ID пользователя и удаляет его, если он существует.
     * </p>
     *
     * @param id ID пользователя.
     * @return ResponseEntity с кодом ответа 200 (OK) или 404 (Not Found).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}