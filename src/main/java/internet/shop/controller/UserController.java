package internet.shop.controller;

import internet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody String body) {
        userService.add(body);
        return new ResponseEntity<>("Successfully saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        userService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody String body) {
        userService.put(id, body);
        return new ResponseEntity<>("Successfully patched", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id) {
        String userString = userService.getOne(id);
        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    /*@GetMapping("")
    public ResponseEntity getMany(){
        String placesString = userService.getMany();
        return new ResponseEntity<> (placesString, HttpStatus.OK);
    }*/
}
