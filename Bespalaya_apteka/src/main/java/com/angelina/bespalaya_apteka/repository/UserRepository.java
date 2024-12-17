package com.angelina.bespalaya_apteka.repository;

import com.angelina.bespalaya_apteka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
