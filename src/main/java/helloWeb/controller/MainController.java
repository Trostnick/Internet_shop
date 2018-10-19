package helloWeb.controller;

import java.time.LocalDate;
import java.util.Optional;

import helloWeb.entity.*;
import helloWeb.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;


@Controller
@RestController
public class MainController {

    private final CampRepository campRepository;

    private final PtypeRepository ptypeRepository;

    private final CtypeRepository ctypeRepository;

    private final UserRepository userRepository;

    private final UstatusRepository ustatusRepository;

    private final OrderRepository orderRepository;

    private final OstatusRepository ostatusRepository;

    private final PlaceRepository placeRepository;

    private final OrderCampIdRepository orderCampIdRepository;

    private final CampPhotoRepository campPhotoRepository;

    @Autowired
    public MainController(CampRepository campRepository, PtypeRepository ptypeRepository,
                          CtypeRepository ctypeRepository, UserRepository userRepository,
                          UstatusRepository ustatusRepository, OrderRepository orderRepository,
                          OstatusRepository ostatusRepository, PlaceRepository placeRepository,
                          OrderCampIdRepository orderCampIdRepository, CampPhotoRepository campPhotoRepository) {
        this.campRepository = campRepository;
        this.ptypeRepository = ptypeRepository;
        this.ctypeRepository = ctypeRepository;
        this.ustatusRepository = ustatusRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.ostatusRepository = ostatusRepository;
        this.placeRepository = placeRepository;
        this.orderCampIdRepository = orderCampIdRepository;
        this.campPhotoRepository = campPhotoRepository;

    }

    @GetMapping("/findcampById")
    public String getCampByID(@RequestParam String id) {
        /*campRepository.findById(Long.valueOf(id)).ifPresent(camp -> {
            String campString = camp.toString();
            return campString;
        });
        return "По данному идентификатору записи не обнаружено";*/
        Optional<Camp> camp = campRepository.findById(Long.valueOf(id));
        if (camp.isPresent()) {
            return camp.get().toString();
        }
        return "По данному идентификатору записи не обнаружено";
    }

    @GetMapping("/findcampByName")
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
    }

    @GetMapping("/allcamp")
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

    @PostMapping("/addcamp")
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
                    ctypeRepository.findById(Long.parseLong(current[1])).ifPresent(newCamp::setType);
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

    @PostMapping("/addplacetype")
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
        ptypeRepository.save(newPtype);
        return "Saved";
    }

    @PostMapping("/addcamptype")
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
        ctypeRepository.save(newCtype);
        return "Saved";
    }

    @PostMapping("/adduser")
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
                    ustatusRepository.findById(Long.parseLong(current[1])).ifPresent(newUser::setStatus);


                    break;


            }

        }
        userRepository.save(newUser);
        return "Saved";
    }

    @PostMapping("/addplace")
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
                    ptypeRepository.findById(Long.parseLong(current[1])).ifPresent(newPlace::setType);
                    break;


            }

        }
        placeRepository.save(newPlace);
        return "Saved";
    }

    @PostMapping("/addorder")
    public String addOrder(@RequestBody String body) {
        Order newOrder = new Order();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "status_id":
                    ostatusRepository.findById(Long.parseLong(current[1])).ifPresent(newOrder::setStatus);
                    break;
                case "user_id":
                    userRepository.findById(Long.parseLong(current[1])).ifPresent(newOrder::setUser);
                    break;


            }

        }
        orderRepository.save(newOrder);
        return "Saved";
    }

    @GetMapping("/removecamp")
    public String removeCampById(@RequestParam String id) {
        Optional<Camp> camp = campRepository.findById(Long.valueOf(id));
        if (camp.isPresent()) {
            String campString = camp.get().toString();
            campRepository.deleteById(Long.valueOf(id));
            return "Удалена запись " + campString;
        }
        return "По данному идентификатору записи не обнаружено";
    }
}
