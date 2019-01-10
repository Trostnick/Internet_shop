package internet.shop.controller.rest;

import internet.shop.model.entity.User;
import internet.shop.model.form.UserForm;
import internet.shop.service.UserService;
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
@RequestMapping("/api/user")
public class RestUserController {

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addClient(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {


        userService.addClient(userForm, bindingResult);

        return new ResponseEntity<>("User successfuly created", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("admin")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        userService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @RolesAllowed("admin")
    public ResponseEntity put(@PathVariable Long id, @RequestBody User newUser) {
        userService.put(id, newUser);
        return new ResponseEntity<>("Successfully putted ", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed("admin")
    public ResponseEntity getOne(@PathVariable() Long id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    /*@GetMapping("")
    public ResponseEntity getMany(){
        String placesString = userService.getMany();
        return new ResponseEntity<> (placesString, HttpStatus.OK);
    }*/
}
