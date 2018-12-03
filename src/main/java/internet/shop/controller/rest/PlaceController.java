package internet.shop.controller.rest;

import internet.shop.model.entity.Place;
import internet.shop.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    @RolesAllowed({"manager","admin"})
    public ResponseEntity add(@Valid @RequestBody Place newPlace, BindingResult bindingResult) {


        return new ResponseEntity<>(placeService.add(newPlace, bindingResult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"admin","manager"})
    public ResponseEntity deleteOne(@PathVariable Long id) {

        placeService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @RolesAllowed({"manager","admin"})
    public ResponseEntity put(@PathVariable Long id, @Valid @RequestBody Place newPlace,
                              BindingResult bindingResult) {
        List<ObjectError> validateErrors = bindingResult.getAllErrors();

        if (!validateErrors.isEmpty()) {
            return new ResponseEntity<>(validateErrors, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(placeService.put(id, newPlace), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        return new ResponseEntity<>(placeService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMany(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(placeService.getMany(name), HttpStatus.OK);
    }


}
