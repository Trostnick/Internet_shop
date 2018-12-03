package internet.shop.controller.rest;

import internet.shop.model.entity.Camp;
import internet.shop.model.filter.CampFilter;
import internet.shop.model.form.CampForm;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;


/**
 * REST контроллер для работы с лагерями
 * позволяет добавлять, изменять, удалять, получать один или несколько лагерей, иконку и маленькую иконку
 * доступ осуществляется по пути "/api/camp"
 *
 *
 * @author Прохоров Дмитрий
 * @version 1.0
 */
@RestController
@RequestMapping("/api/camp")
public class CampController {

    private final CampService campService;

    /**
     * Конструктор, подключающий CampService при помощи аннотации @Autowired
     *
     * @param campService - сервис для работы с базой данных
     * @see CampService
     */
    @Autowired
    public CampController(CampService campService) {
        this.campService = campService;
    }

    /**
     * Данный метод обрабатывает POST запросы по пути "/api/camp" и добавляет новый лагерь
     * Тип данных - multipart-form/data
     * Доступ имеют только пользватели с ролью manager и admin
     *
     * @param campForm - данные о новом лагере
     * @param bindingResult - ошибки Валидации
     * @see CampForm
     * @see CampService
     * @return возвращает ответ со статусом 200. В теле ответа хранится добавленный лагерь в json формате
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity add(@Valid @ModelAttribute CampForm campForm, BindingResult bindingResult) {

        return new ResponseEntity<>(campService.add(campForm, bindingResult), HttpStatus.OK);
    }

    /**
     * Данный метод обрабатывает DELETE запросы по пути "/api/camp/{id}" и удаляет лагерь
     * с введенным id если он есть
     * Доступ имеют только пользватели с ролью manager и admin
     *
     * @param id - id лагеря в базе данных
     * @see CampService
     * @return возвращает ответ со статусом 200 и сообщением "Successfully deleted" в виде строки
     */
    @DeleteMapping("/{id}")
    @RolesAllowed({"admin", "manager"})
    public ResponseEntity deleteOne(@PathVariable Long id) {
        campService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    /**
     * Данный метод обрабатывает PUT запросы по пути "/api/camp/{id}" и заменяет лагерь
     * с введенным id если он есть
     * Доступ имеют только пользватели с ролью manager и admin
     *
     * @param id - id лагеря в базе данных
     * @param newCamp - новые данные для лагеря
     * @see CampService
     * @return возвращает ответ со статусом 200 и данными о замененном лагере в теле в виде json
     */
    @PutMapping("/{id}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity put(@PathVariable Long id, @RequestBody Camp newCamp) {

        return new ResponseEntity<>(campService.put(id, newCamp), HttpStatus.OK);
    }

    /**
     * Данный метод обрабатывает GET запросы по пути "/api/camp/{id}" и возвращает лагерь
     * с введенным id если он есть
     *
     * @param id - id лагеря в базе данных
     * @see CampService
     * @return возвращает ответ со статусом 200 и данными о замененном лагере в теле в виде json
     */
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id) {

        return new ResponseEntity<>(campService.getOne(id), HttpStatus.OK);
    }

    /**
     * Данный метод обрабатывает GET запросы по пути "/api/camp/"
     * и возвращает список лагерей, подходящих под параметры фильтрации
     *
     *
     * @param campFilter - параметры филтьтрации
     * @see CampFilter
     * @see CampService
     * @return возвращает ответ с статусом 200, в теле хранится список List лагерей,
     * подходящих под параметры филтьтрации
     *
     */
    @GetMapping
    public ResponseEntity getMany(CampFilter campFilter) {

        return new ResponseEntity<>(campService.getMany(campFilter), HttpStatus.OK);
    }

    /**
     * Данный метод обрабатывает GET запросы по пути "/icon/{id}" и возвращает иконку лагеря
     * с введенным id
     *
     * @param id - id лагеря в базе данных
     * @see CampService
     * @return возвращает ответ со статусом 200 и массив байт иконки в теле
     */
    @GetMapping("/icon/{id}")
    public byte[] getIcon(@PathVariable Long id) {

        return campService.getOne(id).getIcon();
    }

    /**
     * Данный метод обрабатывает GET запросы по пути "/icon/small/{id}" и возвращает маленькую иконку лагеря
     * с введенным id
     *
     * @param id - id лагеря в базе данных
     * @see CampService
     * @return возвращает ответ со статусом 200 и массив байт маленькой иконки в теле
     */
    @GetMapping("/icon/small/{id}")
    public byte[] getSmallIcon(@PathVariable Long id) {

        return campService.getOne(id).getSmallIcon();
    }

}

