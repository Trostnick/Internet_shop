package internet.shop.controller;


import internet.shop.entity.OrderCamp;
import internet.shop.service.OrderCampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderCamp")
public class OrderCampController {

/*    private final OrderCampService orderCampService;

    @Autowired
    public OrderCampController(OrderCampService orderCampService) {
        this.orderCampService = orderCampService;
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody String body) {
        orderCampService.add(body);
        return new ResponseEntity<> ("Successfully saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id){
        orderCampService.deleteOne(id);
        return new ResponseEntity<> ("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody String body){
        orderCampService.put(id, body);
        return new ResponseEntity<> ("Successfully putted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id){
        OrderCamp orderCamp = orderCampService.getOne(id);
        return new ResponseEntity<> (orderCamp.toString(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getMany(@RequestParam String login,
                                  @RequestParam(required = false, defaultValue = "all") String statusName,
                                  @RequestParam(required = false, defaultValue = "all") String campName) {
        String placesString = orderCampService.getMany(login, statusName, campName );
        return new ResponseEntity<>(placesString, HttpStatus.OK);
    }*/
}
