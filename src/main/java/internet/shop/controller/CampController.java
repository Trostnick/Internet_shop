package internet.shop.controller;

import internet.shop.entity.Camp;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RestController
@RequestMapping("/camp")
public class CampController {

/*    private final CampService campService;

*//*
        public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        Map<String, Object> model = modelAndView.getModel();
        model.put("camps")
        return "home";
    }*//*

    @Autowired
    public CampController(CampService campService) {
        this.campService = campService;
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody Camp camp) {
        campService.add(camp);
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
        return new ResponseEntity<> ("Successfully putted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id){
        Camp camp = campService.getOne(id);
        return new ResponseEntity<> (camp.toString(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getMany(@RequestParam(required = false, defaultValue = "") String name,
                                  @RequestParam(required = false) Integer ageMin,
                                  @RequestParam(required = false, defaultValue = "999") int ageMax) {
        String placesString = campService.getMany(name, ageMin, ageMax);
        return new ResponseEntity<>(placesString, HttpStatus.OK);
    }*/
}
