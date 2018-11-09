package internet.shop.controller.rest;

import internet.shop.entity.Camp;
import internet.shop.filter.CampFilter;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/camp")
public class CampController {

    private final CampService campService;

    @Autowired
    public CampController(CampService campService) {
        this.campService = campService;
    }

    @PostMapping
    @RolesAllowed({"manager","admin"})
    public ResponseEntity add(@Valid @RequestBody Camp newCamp,
                              BindingResult bindingResult) {
        List<ObjectError> validateErrors = bindingResult.getAllErrors();

        if (!validateErrors.isEmpty()) {
            List<String> validateMessages = new ArrayList<>();
            validateErrors.forEach(f -> validateMessages.add(((FieldError) f).getField() + " - " + f.getDefaultMessage()));
            return new ResponseEntity<>(validateMessages, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (newCamp.getDateStart().isAfter(newCamp.getDateFinish())) {
            return new ResponseEntity<>("Дата начала не может быть позже даты окончания", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (newCamp.getAgeMin() > newCamp.getAgeMax()) {
            return new ResponseEntity<>("Минимальный возраст не может быть больше максимлаьного", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(campService.add(newCamp), HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"admin","manager"})
    public ResponseEntity deleteOne(@PathVariable Long id) {
        campService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @RolesAllowed({"manager","admin"})
    public ResponseEntity put(@PathVariable Long id, @RequestBody Camp newCamp) {

        return new ResponseEntity<>(campService.put(id, newCamp), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id) {

        return new ResponseEntity<>(campService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMany(CampFilter campFilter) {

        return new ResponseEntity<>(campService.getMany(campFilter), HttpStatus.OK);
    }


    @GetMapping("/icon/{id}")
    public byte[] getIcon(@PathVariable Long id) {
        return campService.getOne(id).getIcon();
    }

}


 /*@PutMapping("/icon/{id}")
    public ResponseEntity putImage(@PathVariable Long id, @RequestBody String iconPath) {

        Camp camp = campService.getOne(id);

        try {
            camp = campService.putIcon(camp, iconPath);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(camp, HttpStatus.OK);
    }*/