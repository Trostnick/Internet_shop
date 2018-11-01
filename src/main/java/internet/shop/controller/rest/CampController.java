package internet.shop.controller.rest;

import internet.shop.entity.Camp;
import internet.shop.filter.CampFilter;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public ResponseEntity add(@Valid @RequestBody Camp newCamp,
                              @RequestParam String iconPath,
                              @RequestParam
                                      BindingResult bindingResult) {
        List<ObjectError> validateErrors = bindingResult.getAllErrors();
        if (!validateErrors.isEmpty()) {
            return new ResponseEntity<>(validateErrors, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        //Добавление картинки
        try {
            File icon = new File(iconPath);
            newCamp.setIcon(Files.readAllBytes(icon.toPath()));
        } catch (IOException e) {
            new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Camp camp = campService.add(newCamp);

        return new ResponseEntity<>(camp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        campService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
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

    @PutMapping("/icon/{id}")
    public ResponseEntity putImage(@PathVariable Long id, @RequestBody String iconPath) {

        Camp camp = campService.getOne(id);

        try {
            camp = campService.putIcon(camp, iconPath);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(camp, HttpStatus.OK);
    }

    @GetMapping("/icon/{id}")
    public byte[] getIcon(@PathVariable Long id) {
        return campService.getOne(id).getIcon();
    }

}
