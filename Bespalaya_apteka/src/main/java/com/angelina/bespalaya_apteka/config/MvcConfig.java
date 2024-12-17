package com.angelina.bespalaya_apteka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Конфигурационный класс для Spring MVC.
 * Предоставляет настройку разрешения локали, обработку смены языка
 * и регистрацию связанных компонентов.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Определяет бин {@link LocaleResolver}, который отвечает за определение локали
     * пользователя на основе его сессии. По умолчанию локаль установлена на английский (США).
     *
     * @return реализация {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    /**
     * Определяет бин {@link LocaleChangeInterceptor}, который позволяет изменять текущую локаль
     * на основании параметра запроса. Имя параметра - "lang".
     *
     * @return экземпляр {@link LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * Добавляет перехватчики для пред- и пост-обработки вызовов методов контроллеров.
     * Регистрирует {@link LocaleChangeInterceptor} для включения возможности смены локали.
     *
     * @param registry объект {@link InterceptorRegistry} для настройки
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
