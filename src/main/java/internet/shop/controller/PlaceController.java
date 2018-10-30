package internet.shop.controller;

import internet.shop.entity.Place;
import internet.shop.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody Place newPlace, BindingResult bindingResult) {
        List<ObjectError> validateErrors = bindingResult.getAllErrors();

        if (!validateErrors.isEmpty()) {
            return new ResponseEntity<>(validateErrors, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Place>(placeService.add(newPlace), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {

        placeService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
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
        return new ResponseEntity<Place>(placeService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMany(@RequestParam(required = false) String name) {
        return new ResponseEntity<List<Place>>(placeService.getMany(name), HttpStatus.OK);
    }


}
