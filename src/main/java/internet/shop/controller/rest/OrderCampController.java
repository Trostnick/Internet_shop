package internet.shop.controller.rest;


import internet.shop.entity.OrderCamp;
import internet.shop.form.OrderCampCount;
import internet.shop.service.OrderCampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orderCamp")
public class OrderCampController {

    private final OrderCampService orderCampService;

    @Autowired
    public OrderCampController(OrderCampService orderCampService) {
        this.orderCampService = orderCampService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody OrderCamp orderCamp) {

        return new ResponseEntity<>(orderCampService.add(orderCamp), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        orderCampService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody OrderCamp orderCamp) {

        return new ResponseEntity<>(orderCampService.put(id, orderCamp), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {

        return new ResponseEntity<>(orderCampService.getOne(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeCount(@PathVariable Long id, @RequestBody OrderCampCount orderCampCount) {
        return new ResponseEntity<>(orderCampService.changeCount(id, orderCampCount.getNewCount()), HttpStatus.OK);
    }
}
