package hello_web;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;


@Controller
@RestController
public class CampController {

    @Autowired
    private CampRepository campRepository;

    @GetMapping("/findById")
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

    @GetMapping("/findByName")
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

    @GetMapping("/all")
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

    @PostMapping("/add")
    public String addCamp(@RequestBody String body) {
        Camp newCamp = new Camp();
        for (String param : body.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    newCamp.setName(current[1]);
                    break;
                case "dateStart":
                    newCamp.setDateStart(current[1]);
                    break;
                case "dateFinish":
                    newCamp.setDateFinish(current[1]);
                    break;

            }

        }
        campRepository.save(newCamp);
        return "Saved";
    }

    @GetMapping("/remove")
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
