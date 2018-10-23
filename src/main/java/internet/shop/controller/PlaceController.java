package internet.shop.controller;

import internet.shop.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody String body) {
        placeService.add(body);
        return new ResponseEntity<> ("Successfully saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id){
        placeService.deleteOne(id);
        return new ResponseEntity<> ("Successfully deleted", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody String body){
        placeService.put(id, body);
        return new ResponseEntity<> ("Successfully patched", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable() Long id){
        String placeString = placeService.getOne(id);
        return new ResponseEntity<> (placeString, HttpStatus.OK);
    }

/*    @GetMapping("")
    public ResponseEntity getMany(){
        String placesString = placeService.getMany();
        return new ResponseEntity<> (placesString, HttpStatus.OK);
    }*/

    /*private final CampRepository campRepository;

    private final PlaceTypeRepository placeTypeRepository;

    private final CampTypeRepository campTypeRepository;

    private final UserRepository userRepository;

    private final UserStatusRepository userStatusRepository;

    private final OrderRepository orderRepository;

    private final OrderStatusRepository orderStatusRepository;


    private final OrderCampIdRepository orderCampIdRepository;

    private final CampPhotoRepository campPhotoRepository;*/



    /*@GetMapping("/camps")
    public String getCampByName(@RequestParam String name) {

        String resCamp = "";

        for (Camp iCamp : campRepository.findAllByName(name)) {
            resCamp = resCamp.concat(iCamp.toString());
            resCamp = resCamp.concat("\n");
        }
        if (resCamp.isEmpty()) {
            return "Записи не обнаружены";
        }
        return resCamp;
    }*/

    /*@GetMapping("/camps/{id}")
    public String getCampByID(@PathVariable String id) {
        *//*campRepository.findById(Long.valueOf(id)).ifPresent(camp -> {
            String campString = camp.toString();
            return campString;
        });
        return "По данному идентификатору записи не обнаружено";*//*
        Optional<Camp> camp = campRepository.findById(Long.valueOf(id));
        if (camp.isPresent()) {
            return camp.get().toString();
        }
        return "По данному идентификатору записи не обнаружено";
    }

    @GetMapping("/camps")
    public String getCampAll() {

        String resCamp = "";


        for (Camp iCamp : campRepository.findAll()) {
            resCamp = resCamp.concat(iCamp.toString());
            resCamp = resCamp.concat("\n");
        }
        if (resCamp.isEmpty()) {
            return "Записи не обнаружены";
        }
        return resCamp;
    }

    @PostMapping("/camps")
    public String addCamp(@RequestBody String body) {
        Camp newCamp = new Camp();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newCamp.setName(current[1]);
                    break;
                case "dateStart":
                    newCamp.setDateStart(LocalDate.parse(current[1]));
                    break;
                case "dateFinish":
                    newCamp.setDateFinish(LocalDate.parse(current[1]));
                    break;
                case "type_id":
                    campTypeRepository.findById(Long.parseLong(current[1])).ifPresent(newCamp::setType);
                    break;
                case "place_id":
                    placeRepository.findById(Long.parseLong(current[1])).ifPresent(newCamp::setPlace);
                    break;
                case "childrenCount":
                    newCamp.setChildrenCount(Integer.parseInt(current[1]));
                    break;
                case "ageMin":
                    newCamp.setAgeMin(Integer.parseInt(current[1]));
                    break;
                case "ageMax":
                    newCamp.setAgeMax(Integer.parseInt(current[1]));
                    break;
                case "info":
                    newCamp.setInfo(current[1]);
                    break;
                case "icon":
                    newCamp.setIcon(current[1]);
                    break;


            }

        }
        campRepository.save(newCamp);
        return "Saved";
    }

    @PostMapping("/placeTypes")
    public String addPlaceType(@RequestBody String body) {
        PlaceType newPtype = new PlaceType();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newPtype.setName(current[1]);
                    break;


            }

        }
        placeTypeRepository.save(newPtype);
        return "Saved";
    }

    @PostMapping("/campTypes")
    public String addCampType(@RequestBody String body) {
        CampType newCtype = new CampType();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newCtype.setName(current[1]);
                    break;


            }

        }
        campTypeRepository.save(newCtype);
        return "Saved";
    }

    @PostMapping("/users")
    public String addUser(@RequestBody String body) {
        User newUser = new User();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newUser.setName(current[1]);
                    break;
                case "login":
                    newUser.setLogin(current[1]);
                    break;
                case "password":
                    newUser.setPassword(current[1]);
                    break;
                case "status_id":
                    userStatusRepository.findById(Long.parseLong(current[1])).ifPresent(newUser::setStatus);


                    break;


            }

        }
        userRepository.save(newUser);
        return "Saved";
    }

    @PostMapping("/places")
    public String addPlace(@RequestBody String body) {
        Place newPlace = new Place();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newPlace.setName(current[1]);
                    break;
                case "info":
                    newPlace.setInfo(current[1]);
                    break;
                case "adress":
                    newPlace.setAdress(current[1]);
                    break;
                case "type_id":
                    placeTypeRepository.findById(Long.parseLong(current[1])).ifPresent(newPlace::setType);
                    break;


            }

        }
        placeRepository.save(newPlace);
        return "Saved";
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody String body) {
        Order newOrder = new Order();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "status_id":
                    orderStatusRepository.findById(Long.parseLong(current[1])).ifPresent(newOrder::setStatus);
                    break;
                case "user_id":
                    userRepository.findById(Long.parseLong(current[1])).ifPresent(newOrder::setUser);
                    break;


            }

        }
        orderRepository.save(newOrder);
        return "Saved";
    }

    @DeleteMapping("/camps")
    public String removeCampById(@RequestParam String id) {
        Optional<Camp> camp = campRepository.findById(Long.valueOf(id));
        if (camp.isPresent()) {
            String campString = camp.get().toString();
            campRepository.deleteById(Long.valueOf(id));
            return "Удалена запись " + campString;
        }
        return "По данному идентификатору записи не обнаружено";
    }
    */


}
