package internet.shop.controller;

import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/camp")
public class CampController {

    private final CampService campService;

    @Autowired
    public CampController(CampService campService) {
        this.campService = campService;
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody String body) {
        campService.add(body);
        return new ResponseEntity<> ("Successfully saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id){
        campService.deleteOne(id);
        return new ResponseEntity<> ("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody String body){
        campService.put(id, body);
        return new ResponseEntity<> ("Successfully patched", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id){
        String campString = campService.getOne(id);
        return new ResponseEntity<> (campString, HttpStatus.OK);
    }

    /*@GetMapping("")
    public ResponseEntity getMany(){
        String placesString = campService.getMany();
        return new ResponseEntity<> (placesString, HttpStatus.OK);
    }*/
}
