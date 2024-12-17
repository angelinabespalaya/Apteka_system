package com.angelina.bespalaya_apteka.service.impl;

import com.angelina.bespalaya_apteka.dto.UserRegistrationDto;
import com.angelina.bespalaya_apteka.entity.User;
import com.angelina.bespalaya_apteka.repository.UserRepository;
import com.angelina.bespalaya_apteka.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Реализация интерфейса {@link UserService}, предоставляющая функционал для управления пользователями.
 * Сервис обрабатывает данные о пользователях и взаимодействует с репозиторием {@link UserRepository}.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Конструктор с внедрением зависимостей {@link UserRepository} и {@link BCryptPasswordEncoder}.
     *
     * @param userRepository репозиторий для работы с пользователями
     * @param passwordEncoder энкодер для шифрования паролей
     */
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Сохраняет нового пользователя на основе данных регистрации.
     *
     * @param registrationDto объект DTO с данными о регистрации пользователя
     * @return сохранённый пользователь
     */
    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Загружает пользователя по имени пользователя (email).
     *
     * @param username имя пользователя (email)
     * @return данные пользователя для авторизации
     * @throws UsernameNotFoundException если пользователь с указанным email не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRoleToAuthorities(user.getRole())
        );
    }

    /**
     * Преобразует роль пользователя в коллекцию прав доступа.
     *
     * @param role роль пользователя
     * @return коллекция прав доступа
     */
    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Ищет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь
     * @throws RuntimeException если пользователь не найден
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param user обновлённые данные пользователя
     * @return обновлённый пользователь
     */
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
