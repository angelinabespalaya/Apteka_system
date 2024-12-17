package com.angelina.bespalaya_apteka.repository;

import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.angelina.bespalaya_apteka.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
    @Query("SELECT p FROM Medicine p WHERE CONCAT(p.id, ' ' , p.title, ' ', p.compound, ' ', p.method, ' ', p.issueDate, ' ', p.returnDate) LIKE %?1%")
    List<Medicine> search(String keyword);
}

