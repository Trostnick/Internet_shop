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

@RestController
@RequestMapping("/api/camp")
public class CampController {

    private final CampService campService;

    @Autowired
    public CampController(CampService campService) {
        this.campService = campService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity add(@Valid @ModelAttribute CampForm campForm, BindingResult bindingResult) {

        return new ResponseEntity<>(campService.add(campForm, bindingResult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"admin", "manager"})
    public ResponseEntity deleteOne(@PathVariable Long id) {
        campService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @RolesAllowed({"manager", "admin"})
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

