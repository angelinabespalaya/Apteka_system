package com.angelina.bespalaya_apteka.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Конфигурационный класс для настройки безопасности приложения с использованием Spring Security.
 * Определяет правила авторизации, шифрование паролей и цепочку фильтров безопасности.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

    /**
     * Бин для шифрования паролей с использованием алгоритма BCrypt.
     * Используется для хранения безопасных паролей в базе данных.
     *
     * @return экземпляр {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Бин для конфигурации цепочки фильтров безопасности.
     * Определяет правила авторизации для различных URL и настройку форм логина/логаута.
     *
     * @param http объект {@link HttpSecurity} для настройки безопасности
     * @return настроенная цепочка фильтров безопасности
     * @throws Exception может быть выброшено в случае ошибок конфигурации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Отключение защиты от CSRF-атак
                .csrf((csrf) -> csrf.disable())

                // Настройка правил авторизации для различных URL
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/new").hasRole("ADMIN")
                                .requestMatchers("/edit/**").hasRole("ADMIN")
                                .requestMatchers("/save").hasRole("ADMIN")
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/api/v1/medicine/**").hasRole("ADMIN")
                                .requestMatchers("/favicon.ico").permitAll()
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/registration").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/admin/**").permitAll()
                                .anyRequest().authenticated()
                )

                // Настройка формы логина
                .formLogin(
                        form -> form
                                .loginPage("/login")                // URL страницы логина
                                .loginProcessingUrl("/login")       // URL обработки логина
                                .defaultSuccessUrl("/index", true)  // URL перенаправления после успешного входа
                                .permitAll()                        // Доступ для всех
                )

                // Настройка логаута
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL для логаута
                                .permitAll()                                                  // Доступ для всех
                );

        return http.build();
    }
}

