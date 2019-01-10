package internet.shop.controller.rest;


import internet.shop.model.entity.OrderPart;
import internet.shop.model.form.OrderPartCount;
import internet.shop.service.OrderPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orderPart")
public class RestOrderPartController {

    private final OrderPartService orderPartService;

    @Autowired
    public RestOrderPartController(OrderPartService orderPartService) {
        this.orderPartService = orderPartService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody OrderPart orderPart) {

        return new ResponseEntity<>(orderPartService.add(orderPart), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        orderPartService.deleteOne(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody OrderPart orderPart) {

        return new ResponseEntity<>(orderPartService.put(id, orderPart), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {

        return new ResponseEntity<>(orderPartService.getOne(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeCount(@PathVariable Long id, @RequestBody OrderPartCount orderPartCount) {
        return new ResponseEntity<>(orderPartService.changeCount(id, orderPartCount.getNewCount()), HttpStatus.OK);
    }
}
