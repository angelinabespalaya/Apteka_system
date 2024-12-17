package com.angelina.bespalaya_apteka.service;

import com.angelina.bespalaya_apteka.dto.LoginRequest;
import com.angelina.bespalaya_apteka.entity.User;
import com.angelina.bespalaya_apteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки авторизации пользователей.
 * Предоставляет методы для проверки учетных данных и аутентификации.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор с внедрением зависимостей {@link UserRepository} и {@link PasswordEncoder}.
     *
     * @param userRepository репозиторий для работы с данными пользователей
     * @param passwordEncoder энкодер для проверки паролей
     */
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Проверяет учетные данные пользователя.
     *
     * @param loginRequest объект {@link LoginRequest}, содержащий email и пароль
     * @return {@code true}, если учетные данные верны; {@code false} в противном случае
     */
    public boolean authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}
