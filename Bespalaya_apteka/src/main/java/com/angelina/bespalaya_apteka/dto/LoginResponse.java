package com.angelina.bespalaya_apteka.dto;

//Класс для ответа по входу
public class LoginResponse {
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
