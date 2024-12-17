package com.angelina.bespalaya_apteka.controller;


import com.angelina.bespalaya_apteka.MyException;
import com.angelina.bespalaya_apteka.entity.Medicine;
import com.angelina.bespalaya_apteka.service.MedicineService;
//import com.vaadin.flow.router.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * REST API контроллер для управления объектами "Medicine".
 * <p>
 * Этот класс предоставляет эндпоинты для получения данных о лекарствах
 * и добавления новых лекарств в систему.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/medicine")
public class AppResource {

    /**
     * Сервис для управления объектами "Medicine".
     */
    private final MedicineService medicineService;

    /**
     * Конструктор для создания экземпляра AppResource.
     *
     * @param medicineService Сервис, используемый для выполнения операций с объектами "Medicine".
     */
    public AppResource(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    /**
     * Эндпоинт для получения информации о лекарстве по его идентификатору.
     *
     * @param id Идентификатор лекарства.
     * @return Объект "Medicine", если найден, иначе выбрасывает исключение {@link MyException}.
     */
    @GetMapping("/{id}")
    public Medicine findById(@PathVariable long id) {
        return medicineService.findById(id).orElseThrow(() -> new MyException("not found"));
    }

    /**
     * Эндпоинт для создания нового объекта "Medicine".
     *
     * @param medicine Объект "Medicine" для сохранения.
     * @return Сохранённый объект "Medicine" с установленным идентификатором.
     */
    @PostMapping
    public Medicine create(@RequestBody Medicine medicine) {
        return medicineService.save(medicine);
    }
}

