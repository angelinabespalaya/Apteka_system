package com.angelina.bespalaya_apteka.controller;

// Контроллер - это класс, предназначенный для непосредственной обработки запросов от клиента и возвращения результатов.
import java.util.List;

import com.angelina.bespalaya_apteka.service.MedicineService;
import com.angelina.bespalaya_apteka.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

/**
 * Контроллер для управления приложением.
 * <p>
 * Этот класс предоставляет эндпоинты для отображения страниц и управления объектами "Medicine".
 * </p>
 */
@Controller
public class AppController {

    /**
     * Сервис для управления объектами "Medicine".
     */
    @Autowired
    private MedicineService service;

    /**
     * Эндпоинт для отображения главной страницы с поиском лекарств.
     *
     * @param model Модель для передачи данных в представление.
     * @param keyword Ключевое слово для фильтрации списка лекарств.
     * @param authentication Объект аутентификации для определения роли пользователя.
     * @return Имя шаблона главной страницы "index".
     */
    @RequestMapping("/index")
    public String viewHome(Model model, @Param("keyword") String keyword, Authentication authentication) {
        List<Medicine> listMedicine = service.listAll(keyword);
        model.addAttribute("listMedicine", listMedicine);
        model.addAttribute("keyword", keyword);

        // Получение роли пользователя
        if (authentication != null) {

            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER"); // Роль по умолчанию
            model.addAttribute("userRole", role);
        }

        return "index";
    }

    /**
     * Эндпоинт для отображения формы добавления нового лекарства.
     *
     * @param model Модель для передачи данных в представление.
     * @return Имя шаблона формы "new_medicine".
     */
    @RequestMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showNewMedicineForm(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);
        return "new_medicine";
    }

    /**
     * Эндпоинт для сохранения нового или отредактированного лекарства.
     *
     * @param medicine Объект "Medicine" для сохранения.
     * @return Редирект на главную страницу.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        service.save(medicine);
        return "redirect:/index";
    }

    /**
     * Эндпоинт для отображения формы редактирования лекарства.
     *
     * @param id Идентификатор лекарства.
     * @return Объект ModelAndView с шаблоном "edit_medicine" и данными лекарства.
     */
    @RequestMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView showEditMedicineForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_medicine");
        Medicine medicine = service.get(id);
        mav.addObject("medicine", medicine);
        return mav;
    }

    /**
     * Эндпоинт для удаления лекарства.
     *
     * @param id Идентификатор лекарства.
     * @return Редирект на главную страницу.
     */
    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMedicine(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/index";
    }

    /**
     * Эндпоинт для отображения страницы "О нас".
     *
     * @return Имя шаблона страницы "about".
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}


