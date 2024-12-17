package com.angelina.bespalaya_apteka.service;

import java.util.List;
import java.util.Optional;

import com.angelina.bespalaya_apteka.entity.Medicine;
import com.angelina.bespalaya_apteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired; // связь всех зависимостей
import org.springframework.stereotype.Service;
// Service – указывает, что класс является сервисом для реализации бизнес логики.

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository repo;

    public List<Medicine> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public Medicine save(Medicine medicine) {return repo.save(medicine);}

    public Medicine get(Long id) {
        return repo.findById(id).get();
    }

    public Optional<Medicine> findById(long id) {
        return repo.findById(id);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}