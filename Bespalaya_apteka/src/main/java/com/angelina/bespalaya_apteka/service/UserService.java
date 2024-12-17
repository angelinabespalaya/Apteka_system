package com.angelina.bespalaya_apteka.service;

import com.angelina.bespalaya_apteka.dto.UserRegistrationDto;
import com.angelina.bespalaya_apteka.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> findAll();

    User findById(Long id);

    User update(User user);

    void delete(Long id);
}

