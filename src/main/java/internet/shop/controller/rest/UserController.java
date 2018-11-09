package internet.shop.controller.rest;

import internet.shop.entity.User;
import internet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@RolesAllowed("admin")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody User newUser, BindingResult bindingResult) {
        List<ObjectError> validateErrors = bindingResult.getAllErrors();

        if (!validateErrors.isEmpty()) {
            return new ResponseEntity<>(validateErrors, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.add(newUser);

        return new ResponseEntity<>("User successfuly created", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        userService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody User newUser) {
        userService.put(id, newUser);
        return new ResponseEntity<>("Successfully putted ", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    /*@GetMapping("")
    public ResponseEntity getMany(){
        String placesString = userService.getMany();
        return new ResponseEntity<> (placesString, HttpStatus.OK);
    }*/
}
