package com.angelina.bespalaya_apteka.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для управления отображением страниц HTML.
 * <p>
 * Этот класс предоставляет эндпоинты для отображения страниц логина, регистрации,
 * главной страницы и административной панели.
 * </p>
 */
@Controller
public class HomeController {

    /**
     * Эндпоинт для отображения страницы логина.
     *
     * @return Имя шаблона страницы логина "login".
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Эндпоинт для отображения страницы регистрации.
     *
     * @return Имя шаблона страницы регистрации "registration".
     */
    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    /**
     * Эндпоинт для отображения главной страницы.
     *
     * @param model Модель для передачи данных в представление.
     * @return Имя шаблона главной страницы "index".
     */
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Без авторизации");
        }

        return "index";
    }

    /**
     * Эндпоинт для отображения административной панели.
     *
     * @param model Модель для передачи данных в представление.
     * @return Имя шаблона страницы административной панели "admin".
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }
}

