package com.angelina.bespalaya_apteka.controller;

import com.angelina.bespalaya_apteka.dto.UserRegistrationDto;
import com.angelina.bespalaya_apteka.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//api запрос с регистрацией
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }


    @PostMapping
    public ResponseEntity<String> registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.save(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Регистрация успешна.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Адрес электронной почты уже используется");
        }
    }
}
